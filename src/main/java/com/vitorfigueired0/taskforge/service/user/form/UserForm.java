package com.vitorfigueired0.taskforge.service.user.form;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class UserForm {
  private String email;
  private String password;
}
