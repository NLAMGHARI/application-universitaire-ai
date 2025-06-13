package com.example.demo.Service;

import com.example.demo.Model.Filiere;
import com.example.demo.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    public Filiere ajouterFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }
    public boolean updateFiliere(String sigle, Filiere filiereDetails) {
        Optional<Filiere> optionalFiliere = filiereRepository.findBySigleIgnoreCase(sigle);
        if (optionalFiliere.isPresent()) {
            Filiere filiere = optionalFiliere.get();
            // Mettre à jour les champs nécessaires
            filiere.setNom(filiereDetails.getNom());
            filiere.setResponsable(filiereDetails.getResponsable());
            // Mettez à jour d'autres champs si nécessaire

            filiereRepository.save(filiere);  // Enregistrer les modifications
            return true;
        }
        return false;
    }

    public boolean deleteFiliere(String sigle) {
        System.out.println("Recherche de la filière avec le sigle : " + sigle);
        Optional<Filiere> filiereOptional = filiereRepository.findBySigleIgnoreCase(sigle);
        if (filiereOptional.isPresent()) {
            System.out.println("Filière trouvée : " + filiereOptional.get());
            filiereRepository.delete(filiereOptional.get());
            return true;
        }
        System.out.println("Filière non trouvée.");
        return false;
    }


    public Iterable<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Optional<Filiere> getFiliereById(Long id) {
        return filiereRepository.findById(id);
    }
    public Optional<Filiere> getFiliereBySigle(String sigle) {
        return filiereRepository.findBySigleIgnoreCase(sigle);
    }
}
