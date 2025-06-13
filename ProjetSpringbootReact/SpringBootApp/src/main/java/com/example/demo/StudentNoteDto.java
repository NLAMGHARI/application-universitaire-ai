package com.example.demo;

	public class StudentNoteDto {
	    private String nom;
	    private String prenom;
	    private Double note;
	    private Boolean isValide;
	    private Boolean isAbsent;

	    // Getters et Setters
	    public String getNom() { return nom; }
	    public void setNom(String nom) { this.nom = nom; }

	    public String getPrenom() { return prenom; }
	    public void setPrenom(String prenom) { this.prenom = prenom; }

	    public Double getNote() { return note; }
	    public void setNote(Double note) { this.note = note; }

	    public Boolean getIsValide() { return isValide; }
	    public void setIsValide(Boolean isValide) { this.isValide = isValide; }

	    public Boolean getIsAbsent() { return isAbsent; }
	    public void setIsAbsent(Boolean isAbsent) { this.isAbsent = isAbsent; }
	}



