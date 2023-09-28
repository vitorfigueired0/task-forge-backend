package com.vitorfigueired0.taskforge.service.task.form;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@With
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskForm {
  @NotNull
  private String name;
  private String description;

}
