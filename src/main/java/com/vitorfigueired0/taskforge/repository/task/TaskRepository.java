package com.vitorfigueired0.taskforge.repository.task;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@org.springframework.stereotype.Repository
public class TaskRepository implements Repository<Task> {

  private static final String INSERT_TASK = "INSERT INTO TASKS (NAME, DESCRIPTION, START_DATE, STATUS, USER_ID) VALUES " +
    "(?, ?, ?, ?, ?)";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Task> findAll() {
    return null;
  }

  @Override
  public Task findById(Long id) {
    return null;
  }

  @Override
  public Task create(Task task) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, task.getName());
      ps.setString(2, task.getDescription());
      ps.setDate(3, task.getStartDate());
      ps.setString(4, String.valueOf(task.getStatus()));
      ps.setLong(5, task.getOwner().getId());

      return ps;
    }, keyHolder);


    return task.withId(Long.parseLong(keyHolder.getKeys().get("id").toString()));
  }

  @Override
  public Task update(Task task) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
