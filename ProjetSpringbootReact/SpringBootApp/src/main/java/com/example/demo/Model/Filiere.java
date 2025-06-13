package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String responsable;
    private String sigle;
    

    public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	@OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FiliereModule> modules;

    public Filiere() {}

    public Filiere(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<FiliereModule> getModules() {
        return modules;
    }

    public void setModules(Set<FiliereModule> modules) {
        this.modules = modules;
    }
}
