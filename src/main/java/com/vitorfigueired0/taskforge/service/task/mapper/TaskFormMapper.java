package com.vitorfigueired0.taskforge.service.task.mapper;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.service.Mapper;
import com.vitorfigueired0.taskforge.service.task.TaskStatus;
import com.vitorfigueired0.taskforge.service.task.form.TaskForm;
import com.vitorfigueired0.taskforge.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TaskFormMapper implements Mapper<TaskForm, Task> {

  @Override
  public Task map(TaskForm taskForm) {

    return Task.builder()
      .name(taskForm.getName())
      .description(taskForm.getDescription())
      .startDate(new Date(System.currentTimeMillis()))
      .status(TaskStatus.IN_PROGRESS)
      .build();
  }
}
