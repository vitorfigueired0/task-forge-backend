package com.vitorfigueired0.taskforge.service.user;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.user.UserRepository;
import com.vitorfigueired0.taskforge.service.mail.EmailService;
import com.vitorfigueired0.taskforge.service.mail.MailDTO;
import com.vitorfigueired0.taskforge.service.security.TokenService;
import com.vitorfigueired0.taskforge.service.user.form.RegisterForm;
import com.vitorfigueired0.taskforge.service.user.form.UserForm;
import com.vitorfigueired0.taskforge.service.user.form.ValidateCodeForm;
import com.vitorfigueired0.taskforge.service.user.mapper.UserRegisterFormMapper;
import com.vitorfigueired0.taskforge.service.user.mapper.UserViewMapper;
import com.vitorfigueired0.taskforge.service.user.view.LoginView;
import com.vitorfigueired0.taskforge.service.user.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public class UserService {

  private final UserRegisterFormMapper userRegisterFormMapper;
  private final UserRepository repository;
  private final EmailService emailService;
  private final TokenService tokenService;
  private final UserViewMapper userViewMapper;
  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserService(UserRegisterFormMapper userRegisterFormMapper,
                     UserRepository repository,
                     EmailService emailService,
                     TokenService tokenService,
                     UserViewMapper userViewMapper) {
    this.userRegisterFormMapper = userRegisterFormMapper;
    this.repository = repository;
    this.emailService = emailService;
    this.tokenService = tokenService;
    this.userViewMapper = userViewMapper;
  }

  @Transactional
  public void register(RegisterForm registerForm) {
    emailExists(registerForm.getEmail());
    String encodedPassword = passwordEncoder.encode(registerForm.getPassword());

    User user = userRegisterFormMapper.map(registerForm.withPassword(encodedPassword));
    user = repository.create(user);

    sendVerificationCode(user);
  }

  @Transactional
  public void validateCode(ValidateCodeForm validateCodeForm) {
    User byEmail = (User) findByEmail(validateCodeForm.getEmail());

    if(byEmail == null || repository.findCode(byEmail.getId()) == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER NOT REGISTERED/INVALID CODE");
    }

    repository.enableUser(byEmail);
  }

  public LoginView login(UserForm userForm) {
    User userByEmail = repository.findByEmail(userForm.getEmail());

    if(userByEmail == null || !passwordEncoder.matches(userForm.getPassword(), userByEmail.getPassword())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WRONG EMAIL OR PASSWORD");
    }

    String token = tokenService.createToken(userByEmail);
    return LoginView.builder()
      .token(token)
      .build();
  }

  public UserDetails findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public UserView getUserView(Long id) {
    User user = findById(id);
    return userViewMapper.map(user);
  }

  private void emailExists(String email) {
    if (findByEmail(email) != null) {
      ResponseStatusException userAlreadyExists = new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
      userAlreadyExists.printStackTrace();
      throw userAlreadyExists;
    }
  }

  private void sendVerificationCode(User user) {
    Random random = new Random();
    int code = random.nextInt(100000, 999999);

    MailDTO mailDTO = MailDTO.builder()
      .destinyMail(user.getEmail())
      .title("Aqui está seu código de verificação")
      .text(String.valueOf(code))
      .build();

    emailService.sendVerificationCode(mailDTO);
    repository.insertVerificationCode(user.getId(), code);
  }

  public User findById(Long id) {
    User user = repository.findById(id);

    if(user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    return user;
  }
}
