package com.vitorfigueired0.taskforge.repository.task;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.service.task.TaskStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

  @Override
  public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
    return Task.builder()
      .id(rs.getLong("id"))
      .name(rs.getString("name"))
      .description(rs.getString("description"))
      .startDate(rs.getDate("start_date"))
      .endDate(rs.getDate("end_date"))
      .status(TaskStatus.valueOf(rs.getString("status")))
      .build();
  }
}
