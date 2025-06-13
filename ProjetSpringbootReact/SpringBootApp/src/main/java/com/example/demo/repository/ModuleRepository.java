

package com.example.demo.repository;

import com.example.demo.Model.Module;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
	    Optional<Module> findByCode(String code);
}
