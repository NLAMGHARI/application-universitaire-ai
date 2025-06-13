package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Service.ElementService;
import com.example.demo.Service.ProfesseurService;
import com.example.demo.StudentNoteDto;
import com.example.demo.Model.Element;
import com.example.demo.Model.ElementEtudiant;
import com.example.demo.Model.Etudiant;
import com.example.demo.Model.EtudiantDTO;
import com.example.demo.Model.Professeur;
import com.example.demo.Model.StudentDto;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/professeur")
public class ProfesseurController {

    private final ElementService elementService;
    private final ProfesseurService professeurService;

    // Utilisation d'un constructeur pour l'injection des dépendances
    @Autowired
    public ProfesseurController(ElementService elementService, ProfesseurService professeurService) {
        this.elementService = elementService;
        this.professeurService = professeurService;
    }

    // Saisir les notes d'un élément

    @PostMapping("/saisir-notes/{elementId}")
    public ResponseEntity<?> EnregistrerNotes(
            @PathVariable Long elementId,
            @RequestBody List<Map<String, Object>> notesAbsences) {
        try {
            // Appel du service pour la saisie des notes
            elementService.saisirNotes(elementId, notesAbsences);

            return ResponseEntity.ok("Les notes ont été saisies et validées avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur de validation : " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("État non valide : " + e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entité non trouvée : " + e.getMessage());
        } catch (Exception e) {
            // Afficher la trace de l'exception pour le débogage
            e.printStackTrace(); // Optionnel, à utiliser en développement
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Une erreur inattendue est survenue : " + e.getMessage());
        }
    }

    // Créer un professeur
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Professeur> createProfesseur(@RequestBody Professeur professeur) {
        Professeur createdProfesseur = professeurService.createProfesseur(professeur);
        return ResponseEntity.ok(createdProfesseur);
    }

    // Obtenir tous les professeurs
    @GetMapping
    public ResponseEntity<List<Professeur>> getAllProfesseurs() {
        List<Professeur> professeurs = professeurService.getAllProfesseurs();
        return ResponseEntity.ok(professeurs);
    }

    // Obtenir un professeur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable Long id) {
        try {
            Professeur professeur = professeurService.getProfesseurById(id);
            return ResponseEntity.ok(professeur);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Mettre à jour un professeur
    @PutMapping("/{id}")
    public ResponseEntity<Professeur> updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeur) {
        try {
            Professeur updatedProfesseur = professeurService.updateProfesseur(id, professeur);
            return ResponseEntity.ok(updatedProfesseur);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un professeur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable Long id) {
        boolean deleted = professeurService.deleteProfesseur(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }






    // Récupérer les éléments d'un professeur
    @GetMapping("/{idProfesseur}/elements")
    public ResponseEntity<?> recupererElementsDuProfesseur(@PathVariable Long idProfesseur) {
        try {
            List<Element> elements = professeurService.getElementsProfesseur(idProfesseur);
            return ResponseEntity.ok(elements);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/valider-notes/{elementId}")
    public ResponseEntity<?> validerNotes(
            @PathVariable Long elementId,
            @RequestBody List<Map<String, Object>> notesAbsences) {
        try {
            // Appel du service pour la saisie des notes
            elementService.saisirNotes(elementId, notesAbsences);

            // Valider l'élément après la saisie des notes
            elementService.validerElement(elementId, true);

            return ResponseEntity.ok("Les notes ont été saisies et l'élément a été validé avec succès !");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur de validation : " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("État non valide : " + e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entité non trouvée : " + e.getMessage());
        } catch (Exception e) {
            // Afficher la trace de l'exception pour le débogage
            e.printStackTrace(); // Optionnel, à utiliser en développement
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Une erreur inattendue est survenue : " + e.getMessage());
        }
    }

    		
    @GetMapping("/etudiants/{elementId}")
    public ResponseEntity<List<StudentDto>> getEtudiantsByElement(@PathVariable Long elementId) {
        try {
            List<StudentDto> etudiants = elementService.getStudentsNotes2(elementId);
            return ResponseEntity.ok(etudiants);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/etudiantsValide")
    public ResponseEntity<List<String>> getEtudiantsByElementId(@RequestBody Map<String, Long> requestBody) {
        Long elementId = requestBody.get("elementId");
        try {
            List<String> etudiants = elementService.getEtudiantsByElementIdv(elementId);
            return ResponseEntity.ok(etudiants);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/notes")
    public ResponseEntity<List<StudentNoteDto>> getStudentsNotes(@RequestBody Map<String, Long> requestBody) {
        Long elementId = requestBody.get("elementId");
        List<StudentNoteDto> studentNotes = elementService.getStudentsNotes(elementId);
        return ResponseEntity.ok(studentNotes);
    }
}
