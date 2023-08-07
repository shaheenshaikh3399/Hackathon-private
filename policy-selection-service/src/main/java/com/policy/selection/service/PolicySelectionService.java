package com.policy.selection.service;

import com.policy.selection.dto.PolicySelectionDto;
import com.policy.selection.dto.PolicySelectionResponse;
import com.policy.selection.dto.ResponseDto;
import com.policy.selection.entity.PolicySelection;
import org.springframework.stereotype.Service;
@Service
public interface PolicySelectionService {
	ResponseDto saveSelectedPolicy(PolicySelection policySelection, String policyId);
	PolicySelectionResponse getSelectedPolicyDetails(String customerId);
	
}
