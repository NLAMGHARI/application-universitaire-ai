package com.example.demo.repository;

import com.example.demo.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // Vous pouvez ajouter ici des méthodes personnalisées si nécessaire.

    // Exemple d'une méthode pour récupérer un étudiant par son CNE
    Etudiant findByCne(Long cne);
}
