package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Modalite;

@Repository
public interface ModaliteRepository extends JpaRepository<Modalite, Integer> {

	    // Méthode pour trouver une modalité par l'ID de l'élément
	    Modalite findByElementId(Long elementId);
	}


