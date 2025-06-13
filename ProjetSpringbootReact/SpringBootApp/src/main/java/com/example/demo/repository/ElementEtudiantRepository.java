package com.example.demo.repository;


import com.example.demo.Model.ElementEtudiant;
import com.example.demo.Model.ElementEtudiantId;
import com.example.demo.Model.Element;
import com.example.demo.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElementEtudiantRepository extends JpaRepository<ElementEtudiant, ElementEtudiantId> {
    List<ElementEtudiant> findByElement(Element element);
    ElementEtudiant findByElementIdAndEtudiantCne(Long element, Long etudiant);
}
