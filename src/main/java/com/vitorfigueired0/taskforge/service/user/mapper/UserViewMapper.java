package com.vitorfigueired0.taskforge.service.user.mapper;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.service.Mapper;
import com.vitorfigueired0.taskforge.service.user.view.UserView;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper implements Mapper<User, UserView> {

  @Override
  public UserView map(User user) {
    return UserView.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .build();
  }
}
