package com.vitorfigueired0.taskforge.service.user.view;

import lombok.*;

@Getter
@Setter
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
  private Long id;
  private String name;
  private String email;
}
