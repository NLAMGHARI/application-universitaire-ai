package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class ElementEtudiant {

    @EmbeddedId
    private ElementEtudiantId id;

    @ManyToOne
    @MapsId("elementId") // Lier à la clé "elementId" dans ElementEtudiantId
    @JoinColumn(name = "element_id")
    @JsonIgnore
    private Element element;

    @ManyToOne
    @MapsId("etudiantId") // Lier à la clé "etudiantId" dans ElementEtudiantId
    @JoinColumn(name = "etudiant_id")
    @JsonIgnore
    private Etudiant etudiant;

    private Double note;
    private Double noteTp;
    private Double noteProjet;
    private Double noteExamen;

    public ElementEtudiant(ElementEtudiantId id, Element element, Etudiant etudiant, Double note, Double noteTp,
			Double noteProjet, Double noteExamen, Boolean isAbsent, Boolean isValide) {
		super();
		this.id = id;
		this.element = element;
		this.etudiant = etudiant;
		this.note = note;
		this.noteTp = noteTp;
		this.noteProjet = noteProjet;
		this.noteExamen = noteExamen;
		this.isAbsent = isAbsent;
		this.isValide = isValide;
	}

	public Double getNoteTp() {
		return noteTp;
	}

	public void setNoteTp(Double noteTp) {
		this.noteTp = noteTp;
	}

	public Double getNoteProjet() {
		return noteProjet;
	}

	public void setNoteProjet(Double noteProjet) {
		this.noteProjet = noteProjet;
	}

	public Double getNoteExamen() {
		return noteExamen;
	}

	public void setNoteExamen(Double noteExamen) {
		this.noteExamen = noteExamen;
	}

	private Boolean isAbsent = false;

    private Boolean isValide = false;

    // Constructeurs, getters, setters
    public ElementEtudiant() {}

    public ElementEtudiant(Element element, Etudiant etudiant, Double note, Boolean isAbsent, Boolean isValide) {
        this.id = new ElementEtudiantId(element.getId(), etudiant.getCne());
        this.element = element;
        this.etudiant = etudiant;
        this.note = note;
        this.isAbsent = isAbsent;
        this.isValide = isValide;
    }

    public ElementEtudiantId getId() {
        return id;
    }

    public void setId(ElementEtudiantId id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Boolean getIsAbsent() {
        return isAbsent;
    }

    public void setIsAbsent(Boolean isAbsent) {
        this.isAbsent = isAbsent;
    }

    public Boolean getIsValide() {
        return isValide;
    }

    public void setIsValide(Boolean isValide) {
        this.isValide = isValide;
    }

	public ElementEtudiant orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}

