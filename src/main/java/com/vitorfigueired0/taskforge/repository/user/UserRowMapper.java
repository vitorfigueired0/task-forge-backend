package com.vitorfigueired0.taskforge.repository.user;

import com.vitorfigueired0.taskforge.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return User.builder()
      .id(rs.getLong("ID"))
      .name(rs.getString("NAME"))
      .email(rs.getString("EMAIL"))
      .password(rs.getString("PASSWORD_HASH"))
      .isConfirmed(rs.getBoolean("IS_CONFIRMED"))
      .build();
  }
}
