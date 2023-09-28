package com.vitorfigueired0.taskforge.domain;

import com.vitorfigueired0.taskforge.service.task.TaskStatus;
import lombok.*;

import java.sql.Date;

@With
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  private Long id;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private TaskStatus status;
  private User owner;
}
