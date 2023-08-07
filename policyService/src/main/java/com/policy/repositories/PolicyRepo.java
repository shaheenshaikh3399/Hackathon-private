package com.policy.repositories;


import com.policy.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepo extends JpaRepository<Policy,String> {

}
