package com.vitorfigueired0.taskforge.repository.user;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User> {

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public User findById(Long id) {
    return null;
  }

  @Override
  public User create(User user) {
    return null;
  }

  @Override
  public User update(User user) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  public User findByEmail(String email){
    return null;
  }
}
