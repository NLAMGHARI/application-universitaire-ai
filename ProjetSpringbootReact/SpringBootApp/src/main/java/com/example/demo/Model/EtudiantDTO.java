package com.example.demo.Model;

public class EtudiantDTO {
	    private String nom;
	    private String prenom;
	    private Long cne;

	    public EtudiantDTO(String nom, String prenom, Long cne) {
	        this.nom = nom;
	        this.prenom = prenom;
	        this.cne = cne;
	    }

	    // Getters
	    public String getNom() {
	        return nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public Long getCne() {
	        return cne;
	    }
	}



