package com.vitorfigueired0.taskforge.service.user.form;

import lombok.*;

@Getter
@Setter
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCodeForm {
  private String email;
  private Integer code;
}
