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
public class RegisterForm {
  private String name;
  private String email;
  private String password;
}
