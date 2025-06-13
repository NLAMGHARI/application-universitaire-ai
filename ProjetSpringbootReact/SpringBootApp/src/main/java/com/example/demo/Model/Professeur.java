package com.example.demo.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nom;
    private String prenom;
    private String specialite;
    private String adressemail;
    
    
	

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST}) 
    @JoinColumn(name = "id_compte", referencedColumnName = "id") 
    private Compte compte;

    @ManyToMany
    @JoinTable(
        name = "professeur_element",
        joinColumns = @JoinColumn(name = "professeur_id"),
        inverseJoinColumns = @JoinColumn(name = "element_id")
    )
    @JsonIgnore
    private List<Element> elements;

    public Professeur() {
    }



    public Professeur(String nom, String prenom, String specialite, String adressemail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.adressemail = adressemail;
	}

	// Getters & Setters
    public String getAdressemail() {
		return adressemail;
	}

	public void setAdressemail(String adressemail) {
		this.adressemail = adressemail;
	}

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public Compte getCompte() {
        return compte;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
