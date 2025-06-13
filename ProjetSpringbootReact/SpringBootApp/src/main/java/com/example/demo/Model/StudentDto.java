package com.example.demo.Model;

public class StudentDto {
	    private Long cne;          // Code National Étudiant
	    private String nom;          // Nom de l'étudiant
	    private String prenom;       // Prénom de l'étudiant
	    private Double note;         // Note générale
	    private Double noteTP;       // Note de TP
	    private Double noteProjet;   // Note de projet
	    private Double noteFinale;   // Note finale calculée
	    private Boolean isValide;     // Indicateur de validation

	    // Getters et Setters

	    public Long getCne() {
	        return cne;
	    }

	    public void setCne(Long long1) {
	        this.cne = long1;
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

	    public Double getNote() {
	        return note;
	    }

	    public void setNote(Double note) {
	        this.note = note;
	    }

	    public Double getNoteTP() {
	        return noteTP;
	    }

	    public void setNoteTP(Double noteTP) {
	        this.noteTP = noteTP;
	    }

	    public Double getNoteProjet() {
	        return noteProjet;
	    }

	    public void setNoteProjet(Double noteProjet) {
	        this.noteProjet = noteProjet;
	    }

	    public Double getNoteFinale() {
	        return noteFinale;
	    }

	    public void setNoteFinale(Double noteFinale) {
	        this.noteFinale = noteFinale;
	    }

	    public Boolean getIsValide() {
	        return isValide;
	    }

	    public void setIsValide(Boolean isValide) {
	        this.isValide = isValide;
	    }
	    
	}
