package com.example.demo.repository;


import com.example.demo.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}



