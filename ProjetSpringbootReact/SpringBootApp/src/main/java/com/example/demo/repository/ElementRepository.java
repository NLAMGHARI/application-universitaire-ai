package com.example.demo.repository;

import com.example.demo.Model.Element;
import com.example.demo.Model.Professeur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
	    List<Element> findByProfesseursAndIsValideTrue(Professeur professeur);
	    List<Element> findByProfesseursAndIsValideFalse(Professeur professeur);
		Optional<Element> findByLabelleIgnoreCase(String intituleElement);
		List<Element> findByIsValideTrue();
	    
	}

	


