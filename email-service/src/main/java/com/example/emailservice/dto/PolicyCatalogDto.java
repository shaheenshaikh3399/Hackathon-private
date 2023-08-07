package com.example.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyCatalogDto {
    private String policyId;
    private String type;
    private String name;
    private Double amount;
}
