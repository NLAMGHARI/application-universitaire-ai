package com.example.demo.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FiliereModuleId implements Serializable {

    private Long filiereId; // ID de la filière
    private Long moduleId;  // ID du module

    // Constructeurs
    public FiliereModuleId() {
    }

    public FiliereModuleId(Long filiereId, Long moduleId) {
        this.filiereId = filiereId;
        this.moduleId = moduleId;
    }

    // Getters et Setters
    public Long getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(Long filiereId) {
        this.filiereId = filiereId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }


}
