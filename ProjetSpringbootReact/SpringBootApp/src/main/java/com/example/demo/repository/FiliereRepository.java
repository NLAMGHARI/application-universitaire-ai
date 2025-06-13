package com.example.demo.repository;

import com.example.demo.Model.Filiere;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    // Méthode pour trouver une filière par son sigle
	@Query("SELECT f FROM Filiere f WHERE LOWER(f.sigle) = LOWER(:sigle)")
	Optional<Filiere> findBySigleIgnoreCase(@Param("sigle") String sigle);

}
