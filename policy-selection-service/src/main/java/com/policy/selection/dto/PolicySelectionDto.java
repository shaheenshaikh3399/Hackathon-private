package com.policy.selection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicySelectionDto {
	String policySelectionId;
	String customerName;
	String email;
	String policyBenefits;
	String selectedPolicyDuration;
	PolicyCatalogDto policyDetails;

}