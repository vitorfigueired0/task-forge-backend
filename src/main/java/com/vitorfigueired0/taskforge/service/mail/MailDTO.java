package com.vitorfigueired0.taskforge.service.mail;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class MailDTO {
  private String destinyMail;
  private String title;
  private String text;
}
