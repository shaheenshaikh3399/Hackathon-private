package com.example.policygenerationservice.feignservice;

import com.example.policygenerationservice.dto.EmailRequestDto;
import com.example.policygenerationservice.entity.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "POLICY-EMAIL-SERVICE", path = "/notifications/email")
public interface EmailFeignClient {
    @PostMapping("/{id}")
     ResponseEntity<EmailRequestDto> createEmail(@RequestBody EmailRequest emailRequest, @PathVariable("id") String documentId);
}
