package com.policy.services.impl;


import com.policy.dto.PolicyCatalogDto;
import com.policy.entities.Policy;
import com.policy.exception.GlobalExceptionHandler;
import com.policy.repositories.PolicyRepo;
import com.policy.services.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyRepo policyRepo;

    @Override
    public List<PolicyCatalogDto> getAllPolicy() {
        log.info("Inside getAllPolicy");

        return policyRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyCatalogDto getById(String pId) {
        log.info("Inside getById");
        Policy policy = policyRepo.findById(pId).orElseThrow(() -> new GlobalExceptionHandler(
                String.format("Policy Not found with the Id : %s", pId)
        ));
        return mapToDto(policy);
    }

    @Override
    public PolicyCatalogDto savePolicy(Policy policy) {
        log.info("Inside savePolicy");
        policy.setPolicyId(UUID.randomUUID().toString());
        return mapToDto(policyRepo.save(policy));
    }

    private PolicyCatalogDto mapToDto(Policy policy) {
        return PolicyCatalogDto.builder()
                .policyId(policy.getPolicyId())
                .name(policy.getName())
                .amount(policy.getAmount())
                .type(policy.getType())
                .build();
    }
}
