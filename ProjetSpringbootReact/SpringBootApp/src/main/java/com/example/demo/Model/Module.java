package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String nom;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Element> elements;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FiliereModule> filiereModules;

    // Constructeurs
    public Module() {
    }

    public Module(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<FiliereModule> getFiliereModules() {
        return filiereModules;
    }

    public void setFiliereModules(List<FiliereModule> filiereModules) {
        this.filiereModules = filiereModules;
    }
}

