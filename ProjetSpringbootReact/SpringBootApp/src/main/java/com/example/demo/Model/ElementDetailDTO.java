package com.example.demo.Model;

import java.util.List;

public class ElementDetailDTO {
    private String nomElement;
    private List<ProfesseurDetailDTO> professeurs;
    private ModaliteDTO modalite; // Un seul modalité
	public String getNomElement() {
		return nomElement;
	}
	public void setNomElement(String nomElement) {
		this.nomElement = nomElement;
	}
	public List<ProfesseurDetailDTO> getProfesseurs() {
		return professeurs;
	}
	public void setProfesseurs(List<ProfesseurDetailDTO> professeurs) {
		this.professeurs = professeurs;
	}
	public ModaliteDTO getModalite() {
		return modalite;
	}
	public void setModalite(ModaliteDTO modalite) {
		this.modalite = modalite;
	}
    

    // Getters et Setters
}
