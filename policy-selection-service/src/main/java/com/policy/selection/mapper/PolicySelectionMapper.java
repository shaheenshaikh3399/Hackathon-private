package com.policy.selection.mapper;

import com.policy.selection.dto.PolicySelectionDto;
import com.policy.selection.entity.PolicySelection;
import com.policy.selection.service.impl.ApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PolicySelectionMapper {

	private final ApiClient apiClient;
	public PolicySelectionDto policySelectionToDto(PolicySelection policySelection) {
		return PolicySelectionDto.builder()
				.policySelectionId(policySelection.getPolicySelectionId())
				.customerName(policySelection.getCustomerName())
				.email(policySelection.getEmail())
				.policyBenefits(policySelection.getPolicyBenefits())
				.selectedPolicyDuration(policySelection.getSelectedPolicyDuration())
				.policyDetails(apiClient.getById(policySelection.getPolicyId()).getBody())
				.build();
	}

	// apiClient.getByCatalogId(policySelection.getPolicyId()).getBody()
}
