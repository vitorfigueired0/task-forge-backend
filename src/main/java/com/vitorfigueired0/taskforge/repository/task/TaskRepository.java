package com.vitorfigueired0.taskforge.repository.task;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class TaskRepository implements Repository<Task> {

  private static final String INSERT_TASK = "INSERT INTO TASKS (NAME, DESCRIPTION, START_DATE, STATUS, USER_ID) VALUES " +
    "(?, ?, ?, ?, ?)";

  private static final String FIND_TASK_BY_ID = "SELECT * FROM TASKS WHERE ID = ? AND USER_ID = ?";

  private static final String FIND_ALL_TASKS = "SELECT * FROM TASKS WHERE USER_ID = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Task> findAll() {
    User actualUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Task> tasks = jdbcTemplate.query(FIND_ALL_TASKS, new TaskRowMapper(), actualUser.getId());
    return tasks.stream().map(task -> task.withOwner(actualUser)).collect(Collectors.toList());
  }

  @Override
  public Task findById(Long id) {
    User actualUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    try {
      Task task = jdbcTemplate.queryForObject(FIND_TASK_BY_ID, new TaskRowMapper(), id, actualUser.getId());
      return task.withOwner(actualUser);

    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
    }
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
