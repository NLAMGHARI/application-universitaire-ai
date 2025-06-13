package com.example.demo.Model;

public class ModuleDetailsDTO {
    private Long id;
    private String nomModule;
    private String nomFiliere;
    private String classe;
    private Integer numSemestre;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getNumSemestre() {
        return numSemestre;
    }

    public void setNumSemestre(Integer numSemestre) {
        this.numSemestre = numSemestre;
    }
}
