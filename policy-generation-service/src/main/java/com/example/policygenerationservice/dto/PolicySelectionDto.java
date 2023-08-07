package com.example.policygenerationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicySelectionDto {
    private String policySelectionId;
    private String customerName;
    private String email;
    private String policyBenefits;
    private String selectedPolicyDuration;
    private PolicyCatalogDto policyDetails;
}
