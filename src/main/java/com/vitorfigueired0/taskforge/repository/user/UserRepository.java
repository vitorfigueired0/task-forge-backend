package com.vitorfigueired0.taskforge.repository.user;

import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User> {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String INSERT_USER = "INSERT INTO USERS (NAME, EMAIL, PASSWORD_HASH, IS_CONFIRMED)" +
    " VALUES (?, ?, ?, ?)";

  private final String FIND_USER_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL = ?";

  private final String ENABLE_USER = "UPDATE USERS SET IS_CONFIRMED = TRUE WHERE EMAIL = ?";

  private final String INSERT_CONFIRMATION_CODE = "INSERT INTO EMAIL_CONFIRMATION_CODE (USER_ID, CODE) " +
    "VALUES (?, ?)";

  private final String FIND_CONFIRMATION_CODE = "SELECT CODE FROM EMAIL_CONFIRMATION_CODE WHERE USER_ID = ?";

  private final String DELETE_CONFIRMATION_CODE = "DELETE FROM EMAIL_CONFIRMATION_CODE WHERE USER_ID = ?";

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
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPassword());
      ps.setBoolean(4, user.isConfirmed());

      return ps;
    }, keyHolder);


    return user.withId(Long.parseLong(keyHolder.getKeys().get("id").toString()));
  }

  @Override
  public User update(User user) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  public User findByEmail(String email){
    User user = null;

    try{
     user = jdbcTemplate.queryForObject(FIND_USER_BY_EMAIL, new UserRowMapper(), email);
    } catch (EmptyResultDataAccessException ignore) {}
    return user;
  }

  public void insertVerificationCode(Long id, int code) {
    int updated = jdbcTemplate.update(INSERT_CONFIRMATION_CODE, id, code);

    if(updated != 1) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR WHILE SAVING CODE");
    }
  }

  public Integer findCode(Long id) {
    Integer code = null;
    try{
      code = jdbcTemplate.queryForObject(FIND_CONFIRMATION_CODE, Integer.class, id);
    } catch (EmptyResultDataAccessException ignore){}
    return code;
  }

  public void enableUser(User user) {
    int updated = jdbcTemplate.update(ENABLE_USER, user.getEmail());

    if(updated != 1) {
      throw new RuntimeException("ERROR IN VALIDATE");
    }
    jdbcTemplate.update(DELETE_CONFIRMATION_CODE, user.getId());
  }


}
