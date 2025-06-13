package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cne;

    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<ElementEtudiant> etudiantElements;

    // Constructeurs, getters, setters
    public Etudiant() {}

    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getCne() {
		return cne;
	}

	public void setCne(Long cne) {
		this.cne = cne;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<ElementEtudiant> getEtudiantElements() {
        return etudiantElements;
    }

    public void setEtudiantElements(List<ElementEtudiant> etudiantElements) {
        this.etudiantElements = etudiantElements;
    }
}

