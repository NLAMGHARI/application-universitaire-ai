package com.example.demo.Controller;

import com.example.demo.Model.Element;
import com.example.demo.Model.ElementDetailDTO;
import com.example.demo.Model.Professeur;
import com.example.demo.Service.ModuleService;
import com.example.demo.Service.ProfesseurService;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProfesseurService professeurservice;

    
    @PostMapping("/ajouter-element")
    public ResponseEntity<String> ajouterElement(@RequestBody Map<String, Object> requestBody) {
        try {
            Long moduleId = ((Number) requestBody.get("moduleId")).longValue();
            String labelle = (String) requestBody.get("labelle");
            Double coefficient = ((Number) requestBody.get("coefficient")).doubleValue();
            Float tp = ((Number) requestBody.get("tp")).floatValue();
            Float exam = ((Number) requestBody.get("exam")).floatValue();
            Float projet = ((Number) requestBody.get("projet")).floatValue();
            Long professeurId = Long.valueOf(requestBody.get("professeurId").toString());

            // Création de l'élément
            Element element = new Element();
            element.setLabelle(labelle);
            element.setCoefficient(coefficient);
            element.setIsValide(false);

            // Ajout de l'élément au module
            ResponseEntity<String> response = moduleService.ajouterElementDansModule(moduleId, element);

            // Vérifiez si l'élément a été ajouté avec succès avant d'ajouter les modalités
            if (response.getStatusCode().is2xxSuccessful()) {
                // Ajout à la table Modalite avec l'élément
                professeurservice.ajouterModalite(tp, exam, projet, element);

                // Appeler le service pour ajouter l'élément au professeur
                Professeur updatedProfesseur = professeurservice.ajouterElementSansSupprimer(professeurId, labelle);

                return ResponseEntity.ok("Élément et modalités ajoutés avec succès.");
            } else {
                return ResponseEntity.status(response.getStatusCode())
                                     .body("Erreur lors de l'ajout de l'élément.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erreur lors de l'ajout de l'élément ou des modalités : " + e.getMessage());
        }
    }
    @PostMapping("/elements/details")
    public ResponseEntity<List<ElementDetailDTO>> getDetails(@RequestBody Map<String, Long> request) {
        Long moduleId = request.get("moduleId");
        List<ElementDetailDTO> elementDetails = moduleService.getDetailsElementsDuModule(moduleId);
        return ResponseEntity.ok(elementDetails);
    }

   


    @GetMapping("/{moduleId}/elements")
    public List<Element> recupererElements(@PathVariable Long moduleId) {
        return moduleService.recupererElements(moduleId);
    }
    @DeleteMapping("/supprimer-element/{elementId}")
    public String supprimerElement(@PathVariable Long elementId) {
        return moduleService.supprimerElement(elementId);
    }
}
