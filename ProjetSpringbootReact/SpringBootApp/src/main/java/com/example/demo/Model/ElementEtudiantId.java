package com.example.demo.Model;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ElementEtudiantId implements Serializable {

    private Long elementId;
    private Long etudiantId;

    // Constructeurs, getters, setters, equals, et hashCode
    public ElementEtudiantId() {}

    public ElementEtudiantId(Long elementId, Long etudiantId) {
        this.elementId = elementId;
        this.etudiantId = etudiantId;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementEtudiantId that = (ElementEtudiantId) o;
        return Objects.equals(elementId, that.elementId) && Objects.equals(etudiantId, that.etudiantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementId, etudiantId);
    }
}
