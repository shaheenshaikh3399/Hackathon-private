package com.example.policygenerationservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
    private String notificationId;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String emailBody;
}
