package com.policy.selection.entity;

//import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="SelectedPolicies")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PolicySelection {
	@Id
	String policySelectionId;
	String policyId;
	String customerName;
	String email;
	String policyBenefits;
	String selectedPolicyDuration;
}