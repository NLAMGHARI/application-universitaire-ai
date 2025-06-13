package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Element {

    public static final int ALIGN_CENTER = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id;

    private String labelle;
    private Double coefficient;
    private Boolean isValide = false;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false) // Association avec Module
    @JsonIgnore
    private Module module; // Relation ManyToOne avec Module
  
    @ManyToMany(mappedBy = "elements", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}) 
    private List<Professeur> professeurs = new ArrayList<>(); // Association ManyToMany avec Professeur

    @OneToMany(mappedBy = "element", cascade = CascadeType.ALL) // Relation OneToMany
    private List<Modalite> modalites = new ArrayList<>(); // Liste des modalités associées

    // Constructeur par défaut
    public Element() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getLabelle() {
        return labelle;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public Module getModule() {
        return module;
    }

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public Boolean getIsValide() {
        return isValide;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setProfesseurs(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }

    public void setIsValide(Boolean isValide) {
        this.isValide = isValide;
    }

    public List<Modalite> getModalites() {
        return modalites;
    }

    public void setModalites(List<Modalite> modalites) {
        this.modalites = modalites;
    }

    public void addModalite(Modalite modalite) {
        if (this.modalites == null) {
            this.modalites = new ArrayList<>();
        }
        this.modalites.add(modalite);
        modalite.setElement(this); // Associer la modalité à cet élément
    }
}

