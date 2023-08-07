package com.example.emailservice.openfeign;

import com.example.emailservice.dto.PolicyDocumentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICY-GENERATION-SERVICE", path = "/policies/generate")
public interface GenerationFeignClient {
    @GetMapping("/getById/{id}")
    ResponseEntity<PolicyDocumentDto> getPolicyDocumentById(@PathVariable("id") String documentId);
}
