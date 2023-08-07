package com.policy.selection.service.impl;


import com.policy.selection.dto.PolicyCatalogDto;
import com.policy.selection.dto.PolicySelectionDto;
import com.policy.selection.dto.PolicySelectionResponse;
import com.policy.selection.dto.ResponseDto;
import com.policy.selection.entity.PolicySelection;
import com.policy.selection.exception.GlobalExceptionHandler;
import com.policy.selection.mapper.PolicySelectionMapper;
import com.policy.selection.repository.PolicySelectionRepository;
import com.policy.selection.service.PolicySelectionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Slf4j
@AllArgsConstructor
public class PolicySelectionImpl implements PolicySelectionService{

	private final PolicySelectionRepository policySelectionRepository;

	private final ApiClient apiClient;
	private final PolicySelectionMapper policySelectionMapper;
	
	
	@Override
	public ResponseDto saveSelectedPolicy(PolicySelection policySelection, String policyId) {
		log.info("Inside saveSelectedPolicy");
		policySelection.setPolicySelectionId(UUID.randomUUID().toString());
		policySelection.setPolicyId(policyId);
		PolicySelectionDto policySelectionDto= policySelectionMapper.policySelectionToDto(policySelectionRepository.save(policySelection));
		return ResponseDto.builder()
				.message("The total amount to be paid for the policy selected: "+policySelectionDto.getPolicyDetails().getAmount())
				.build();
	}

	@Override
	@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultPolicySelection")
	public PolicySelectionResponse getSelectedPolicyDetails(String policySelectionId) {
		log.info("Inside getSelectedPolicyDetails");
		PolicySelection policySelection=  policySelectionRepository.findById(policySelectionId).orElseThrow(()-> new GlobalExceptionHandler(
				String.format("Policy document not found with the id: %s", policySelectionId)
		));
		return PolicySelectionResponse.builder()
				.policySelectedDetails(policySelectionMapper.policySelectionToDto(policySelection))
				.build();
	}
	
	public PolicySelectionResponse getDefaultPolicySelection(String selectionId, Exception exception) {
		PolicySelection policySelection= policySelectionRepository.findById(selectionId).orElseThrow(()-> new GlobalExceptionHandler(
				String.format("Policy document not found with the id: %s", selectionId)
		));
		PolicyCatalogDto policyCatalogDto = PolicyCatalogDto.builder()
				.policyId("288d8caa-7e0c-4b1a-b29a-bb3b9a85acba")
				.name("Dummy Name")
				.type("Dummy type")
				.amount(00000.00)
				.build();
		return PolicySelectionResponse.builder()
				.policySelectedDetails(PolicySelectionDto.builder()
						.policySelectionId(policySelection.getPolicySelectionId())
						.customerName(policySelection.getCustomerName())
						.policyDetails(policyCatalogDto)
						.email(policySelection.getEmail())
						.policyBenefits(policySelection.getPolicyBenefits())
						.selectedPolicyDuration(policySelection.getSelectedPolicyDuration())
						.build())
				.build();
	}

}
