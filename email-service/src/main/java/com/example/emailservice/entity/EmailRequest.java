package com.example.emailservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EmailRequest {
    @Id
    private String notificationId;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String documentId;
    @Length(min = 30, max = 1000000)
    private String emailBody;
}
