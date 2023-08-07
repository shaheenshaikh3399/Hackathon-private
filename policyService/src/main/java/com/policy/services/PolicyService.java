package com.policy.services;


import com.policy.dto.PolicyCatalogDto;
import com.policy.entities.Policy;

import java.util.List;

public interface PolicyService {
    List<PolicyCatalogDto> getAllPolicy();
    PolicyCatalogDto getById(String pId);
    PolicyCatalogDto savePolicy(Policy policy);
}
