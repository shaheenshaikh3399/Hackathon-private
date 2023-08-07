package com.policy.controllers;

import com.policy.dto.PolicyCatalogDto;
import com.policy.entities.Policy;
import com.policy.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;
    @GetMapping("/")
    public ResponseEntity<List<PolicyCatalogDto>> getAllPolicy(){
         return  new ResponseEntity<>(policyService.getAllPolicy(), HttpStatus.OK) ;
    }
    @GetMapping("/{pId}")
    public ResponseEntity<PolicyCatalogDto> getById(@PathVariable String pId){
        return new ResponseEntity<>(policyService.getById(pId),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<PolicyCatalogDto> createPolicy(@RequestBody Policy policy){
        return new ResponseEntity<>(policyService.savePolicy(policy), HttpStatus.CREATED);
    }
}
