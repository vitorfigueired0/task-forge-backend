package com.vitorfigueired0.taskforge.service.user;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.user.UserRepository;
import com.vitorfigueired0.taskforge.service.user.form.RegisterForm;
import com.vitorfigueired0.taskforge.service.user.form.UserForm;
import com.vitorfigueired0.taskforge.service.user.mapper.UserRegisterFormMapper;
import com.vitorfigueired0.taskforge.service.user.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public class UserService {

  @Autowired
  private UserRegisterFormMapper userRegisterFormMapper;

  @Autowired
  private UserRepository repository;

  public void register(RegisterForm registerForm) {
    emailExists(registerForm.getEmail());

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(registerForm.getPassword());

    User user = userRegisterFormMapper.map(registerForm.withPassword(encodedPassword));
    repository.create(user);

    sendVerificationCode(user.getEmail());
  }

  public UserDetails findByEmail(String username) {
    return null;
  }

  private void emailExists(String email) {
    if (findByEmail(email) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
    }
  }

  private void sendVerificationCode(String email) {
    Random random = new Random();
    int code = random.nextInt(100000, 999999);
  }

  public LoginView login(UserForm userForm) {


    return null;
  }
}
