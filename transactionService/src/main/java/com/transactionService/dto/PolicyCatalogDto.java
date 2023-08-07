package com.transactionService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyCatalogDto {
	String policyId;
	String name;
	String type;
	Double amount;
}
