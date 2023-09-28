package com.vitorfigueired0.taskforge.service.task.mapper;

import com.vitorfigueired0.taskforge.domain.Task;
import com.vitorfigueired0.taskforge.domain.User;
import com.vitorfigueired0.taskforge.service.Mapper;
import com.vitorfigueired0.taskforge.service.task.view.TaskView;
import com.vitorfigueired0.taskforge.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TaskViewMapper implements Mapper<Task, TaskView> {

  @Autowired
  private UserService userService;

  @Override
  public TaskView map(Task task) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return TaskView.builder()
      .id(task.getId())
      .name(task.getName())
      .description(task.getDescription())
      .owner(userService.getUserView(user.getId()))
      .startDate(task.getStartDate())
      .endDate(task.getEndDate())
      .status(task.getStatus())
      .build();
  }
}
