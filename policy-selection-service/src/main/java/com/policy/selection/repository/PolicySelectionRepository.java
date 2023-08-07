package com.policy.selection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.selection.entity.PolicySelection;

public interface PolicySelectionRepository extends JpaRepository<PolicySelection, String> {

}
