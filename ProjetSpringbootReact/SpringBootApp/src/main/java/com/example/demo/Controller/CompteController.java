package com.example.demo.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Compte;
import com.example.demo.Service.CompteService;
import com.example.demo.Service.ProfesseurService;

@RestController
@RequestMapping("/compte")  // Optionnel, pour centraliser les routes des comptes sous un seul préfixe
public class CompteController {

    @Autowired
    private CompteService compteService;

    @Autowired
    private ProfesseurService professeurService;

    // Méthode de connexion (déjà existante)
    @PostMapping("/login")

    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, Object> response = professeurService.checkUsernamePassword(username, password);
        return ResponseEntity.ok(response);
    }

    // Créer un nouveau compte
    @PostMapping("/create")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte createdCompte = compteService.createCompte(compte);
        return ResponseEntity.ok(createdCompte);
    }

    // Supprimer un compte par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompte(@PathVariable int id) {
        boolean isDeleted = compteService.deleteCompte(id);
        if (isDeleted) {
            return ResponseEntity.ok("Compte supprimé avec succès");
        } else {
            return ResponseEntity.status(404).body("Compte non trouvé");
        }
    }

    // Mettre à jour un compte
    @PutMapping("/update/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable int id, @RequestBody Compte compte) {
        Compte updatedCompte = compteService.updateCompte(id, compte);
        if (updatedCompte != null) {
            return ResponseEntity.ok(updatedCompte);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
