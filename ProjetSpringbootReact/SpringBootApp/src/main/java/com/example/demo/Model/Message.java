package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages") // Nom de la table dans la base de données
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id; // Identifiant du message

    private String titre; // Titre du message
    private String description; // Description du message
    private String nom; // Nom de l'expéditeur
    private String prenom; // Prénom de l'expéditeur

    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour titre
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Getter et Setter pour description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et Setter pour nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
