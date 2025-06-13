package com.example.demo.repository;

import com.example.demo.Model.Professeur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {


    // Exemple de méthode de recherche avancée, basée sur la spécialité
    List<Professeur> findBySpecialite(String specialite);
    // Méthode pour rechercher un professeur avec la clé étrangère id_compte
    Optional<Professeur> findByCompteId(Integer idCompte);
	Optional<Professeur> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

    // Vous pouvez ajouter d'autres méthodes de recherche selon vos besoins
}
