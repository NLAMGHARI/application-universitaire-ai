package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {

    // Méthode pour vérifier si le nom d'utilisateur et le mot de passe sont corrects
    @Query("SELECT c FROM Compte c WHERE c.Username = :username AND c.Password = :password")
    Compte checkUsernamePassword(String username, String password);

}
