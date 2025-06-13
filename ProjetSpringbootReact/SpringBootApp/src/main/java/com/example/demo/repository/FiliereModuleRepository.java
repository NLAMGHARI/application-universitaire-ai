package com.example.demo.repository;

import com.example.demo.Model.FiliereModule;
import com.example.demo.Model.FiliereModuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereModuleRepository extends JpaRepository<FiliereModule, FiliereModuleId> {
}
