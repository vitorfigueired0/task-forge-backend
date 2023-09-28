package com.vitorfigueired0.taskforge.service.task.view;

import com.vitorfigueired0.taskforge.service.task.TaskStatus;
import com.vitorfigueired0.taskforge.service.user.view.UserView;
import lombok.*;

import java.util.Date;

@With
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskView {
  private Long id;
  private UserView owner;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private TaskStatus status;
}
