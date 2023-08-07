package com.transactionService.openfeign;

import com.transactionService.dto.PolicySelectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICY-SELECTION-SERVICE", path = "/policies/select")
public interface SelectionFeignClient {
    @GetMapping("/details/{id}")
    public ResponseEntity<PolicySelectionResponse> getSelectedDetailsById(@PathVariable("id") String policySelectionId);
}
