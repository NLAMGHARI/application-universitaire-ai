package com.example.demo;

public class EtudiantDto {

    private String nomEtudiant;
    private String prenomEtudiant;
    private String cneEtudiant;
    private Double note;
    private Integer absences;

    // Constructeurs, getters et setters
    public EtudiantDto(String nomEtudiant, String prenomEtudiant, String cneEtudiant, Double note, Integer absences) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.cneEtudiant = cneEtudiant;
        this.note = note;
        this.absences = absences;
    }

	public EtudiantDto(String nom, String prenom, Long cne) {
		// TODO Auto-generated constructor stub
	}

    // Getters et setters
}
