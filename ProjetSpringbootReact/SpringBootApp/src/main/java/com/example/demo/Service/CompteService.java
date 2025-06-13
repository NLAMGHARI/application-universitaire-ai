package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Compte;
import com.example.demo.repository.CompteRepository;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    // Vérifier les informations d'identification
    public boolean checkCredentials(String username, String password) {
        Compte compte = compteRepository.checkUsernamePassword(username, password);
        return compte != null;
    }

    // Créer un compte
    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte); // save() va insérer un nouveau compte si l'ID est nul
    }

    // Supprimer un compte
    public boolean deleteCompte(int id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Mettre à jour un compte
    public Compte updateCompte(int id, Compte compteDetails) {
        if (compteRepository.existsById(id)) {
            Compte compte = compteRepository.findById(id).get();
            compte.setUsername(compteDetails.getUsername());
            compte.setPassword(compteDetails.getPassword());
            return compteRepository.save(compte); // save() va mettre à jour le compte
        }
        return null;
    }
}
