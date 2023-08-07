package com.example.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequestDto {
    private String notificationId;
    private String from;
    private String to;
    private String subject;
    private PolicyDocumentDto body;
}
