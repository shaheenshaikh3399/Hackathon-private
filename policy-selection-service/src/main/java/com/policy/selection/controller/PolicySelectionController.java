package com.policy.selection.controller;

import com.policy.selection.dto.PolicySelectionResponse;
import com.policy.selection.dto.ResponseDto;
import com.policy.selection.entity.PolicySelection;
import com.policy.selection.service.PolicySelectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policies/select")
@AllArgsConstructor
public class PolicySelectionController {

    private final PolicySelectionService policySelectionService;


    @PostMapping("/{id}")
    public ResponseEntity<ResponseDto> saveSelectedPolicies(@RequestBody PolicySelection policySelection, @PathVariable("id") String policyId) {
        return new ResponseEntity<ResponseDto>(policySelectionService.saveSelectedPolicy(policySelection, policyId), HttpStatus.CREATED);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<PolicySelectionResponse> getSelectedDetailsById(@PathVariable("id") String policySelectionId) {
        PolicySelectionResponse apiResponseDto = policySelectionService.getSelectedPolicyDetails(policySelectionId);
        return new ResponseEntity<PolicySelectionResponse>(apiResponseDto, HttpStatus.OK);
    }


}
