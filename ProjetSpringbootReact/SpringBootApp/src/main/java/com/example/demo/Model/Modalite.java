package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity // Indique que cette classe est une entité JPA
@Table(name = "modalite") // Indique le nom de la table correspondante dans la base de données
public class Modalite {

    @Id // Indique que ce champ est la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private int id;

    private float tp; // Utilisation de la convention camelCase
    private float examen; // Utilisation de la convention camelCase
    private float projet;

    @ManyToOne // Relation ManyToOne
    @JoinColumn(name = "element_id", nullable = false) // Colonne de jointure
    @JsonIgnore
    private Element element;
 // Correction du nom de la variable

    public Modalite() {
        // Constructeur par défaut
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTp() {
        return tp; // Utilisation de la variable en camelCase
    }

    public void setTp(float tp) {
        this.tp = tp; // Utilisation de la variable en camelCase
    }

    public float getExamen() {
        return examen; // Utilisation de la variable en camelCase
    }

    public void setExamen(float examen) {
        this.examen = examen; // Utilisation de la variable en camelCase
    }

    public float getProjet() {
        return projet;
    }

    public void setProjet(float projet) {
        this.projet = projet;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}

