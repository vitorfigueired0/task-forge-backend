package com.vitorfigueired0.taskforge.service.user.mapper;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.service.Mapper;
import com.vitorfigueired0.taskforge.service.user.form.RegisterForm;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterFormMapper implements Mapper<RegisterForm, User> {

  @Override
  public User map(RegisterForm registerForm) {

    User user = User.builder()
      .name(registerForm.getName())
      .email(registerForm.getEmail())
      .password(registerForm.getPassword())
      .isConfirmed(Boolean.FALSE)
      .build();

    return user;
  }
}
