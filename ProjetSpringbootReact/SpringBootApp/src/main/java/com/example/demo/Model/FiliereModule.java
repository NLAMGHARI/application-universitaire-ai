package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class FiliereModule {

    @EmbeddedId
    private FiliereModuleId id; // Clé primaire composite

    private Integer numSemestre;
    private String classe;

    @ManyToOne
    @MapsId("filiereId") // Mappe filiereId avec l'attribut de la clé composite
    @JoinColumn(name = "filiere_id")
    @JsonIgnore
    private Filiere filiere;

    @ManyToOne
    @MapsId("moduleId") // Mappe moduleId avec l'attribut de la clé composite
    @JoinColumn(name = "module_id")
    @JsonIgnore
    private Module module;

    // Constructeurs
    public FiliereModule() {
    }

    public FiliereModule(FiliereModuleId id, Filiere filiere, Module module, Integer numSemestre,String classe) {
        this.id = id;
        this.filiere = filiere;
        this.module = module;
        this.numSemestre = numSemestre;
        this.classe=classe;
    }

    // Getters et Setters
    public FiliereModuleId getId() {
        return id;
    }

    public void setId(FiliereModuleId id) {
        this.id = id;
    }

    public Integer getNumSemestre() {
        return numSemestre;
    }

    public void setNumSemestre(Integer numSemestre) {
        this.numSemestre = numSemestre;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
    
}

