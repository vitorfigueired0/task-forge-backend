package com.vitorfigueired0.taskforge.service.user.form;

import lombok.*;

@Getter
@Setter
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
  private String name;
  private String email;
  private String password;
}
