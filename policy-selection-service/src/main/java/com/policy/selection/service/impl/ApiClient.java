package com.policy.selection.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.policy.selection.dto.PolicyCatalogDto;

@FeignClient(name = "POLICY-SERVICE", path="/policies")
public interface ApiClient {
    @GetMapping("/{pId}")
    ResponseEntity<PolicyCatalogDto> getById(@PathVariable String pId);


}
