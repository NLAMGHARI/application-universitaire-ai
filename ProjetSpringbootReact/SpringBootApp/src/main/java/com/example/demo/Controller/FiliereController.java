package com.example.demo.Controller;

import com.example.demo.Model.Module;
import com.example.demo.Model.Filiere;
import com.example.demo.Model.FiliereModule;
import com.example.demo.Model.FiliereModuleId;
import com.example.demo.Service.FiliereModuleService;
import com.example.demo.Service.FiliereService;
import com.example.demo.Service.ModuleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.ModuleDetailsDTO;

@RestController
@RequestMapping("/filiere")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private FiliereModuleService filiereModuleService;

    @PostMapping("/ajouter-module")
    public ResponseEntity<String> ajouterModuleDansFiliere(@RequestBody Map<String, Object> requestBody) {
        String nomModule = (String) requestBody.get("nomModule"); // Nom du module à ajouter
        String sigleFiliere = (String) requestBody.get("sigle"); // Sigle de la filière
        Integer numSemestre = (Integer) requestBody.get("numSemestre");
        String classe = (String) requestBody.get("classe");

        // 1. Récupérer la filière par son sigle
        Optional<Filiere> filiereOpt = filiereService.getFiliereBySigle(sigleFiliere);
        if (!filiereOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Filière non trouvée avec le sigle : " + sigleFiliere);
        }
        Filiere filiere = filiereOpt.get();

        // 2. Créer un nouveau module
        Module nouveauModule = new Module();
        nouveauModule.setNom(nomModule);
        nouveauModule.setCode((String) requestBody.get("code")); // Ajout du code du module
        moduleService.save(nouveauModule); // Enregistrer le module

        // 3. Créer une nouvelle instance de FiliereModule
        FiliereModuleId id = new FiliereModuleId();
        id.setFiliereId(filiere.getId());
        id.setModuleId(nouveauModule.getId());

        FiliereModule filiereModule = new FiliereModule();
        filiereModule.setId(id);
        filiereModule.setFiliere(filiere);
        filiereModule.setModule(nouveauModule);
        filiereModule.setNumSemestre(numSemestre);
        filiereModule.setClasse(classe); // Assurez-vous d'ajouter un setter pour la classe si nécessaire

        // 4. Enregistrer la nouvelle relation dans la base de données
        filiereModuleService.save(filiereModule);

        return ResponseEntity.ok("Module ajouté à la filière avec succès.");
    }
    @GetMapping("/modules")
    
    public ResponseEntity<List<ModuleDetailsDTO>> getAllModules() {
    	
        List<ModuleDetailsDTO> moduleDetailsDTOs = new ArrayList<>();

        // Récupérer toutes les associations entre modules et filières
        List<FiliereModule> filiereModules = filiereModuleService.findAll();

        for (FiliereModule filiereModule : filiereModules) {
            Module module = filiereModule.getModule();
            Filiere filiere = filiereModule.getFiliere();
            
            ModuleDetailsDTO moduleDetailsDTO = new ModuleDetailsDTO();
            moduleDetailsDTO.setId(module.getId());
            moduleDetailsDTO.setNomModule(module.getNom());
            moduleDetailsDTO.setNomFiliere(filiere != null ? filiere.getSigle() : "Inconnu");
            moduleDetailsDTO.setClasse(filiereModule.getClasse());
            moduleDetailsDTO.setNumSemestre(filiereModule.getNumSemestre());
            
            moduleDetailsDTOs.add(moduleDetailsDTO);
        }

        return ResponseEntity.ok(moduleDetailsDTOs);
    }




    @PostMapping("/ajouter")
    public ResponseEntity<Filiere> ajouterFiliere(@RequestBody Filiere filiere) {
        return ResponseEntity.ok(filiereService.ajouterFiliere(filiere));
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> deleteFiliere(@RequestBody Map<String, String> requestBody) {
        String sigle = requestBody.get("sigle");
        boolean deleted = filiereService.deleteFiliere(sigle);
        if (deleted) {
            return ResponseEntity.ok("{\"message\":\"Filière supprimée avec succès.\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Filière non trouvée.\"}");
    }
    @PutMapping("/modifier")
    public ResponseEntity<String> modifierFiliere(@RequestBody Map<String, Object> requestBody) {
        String sigle = (String) requestBody.get("sigle");
        Filiere filiereDetails = new Filiere();
        
        filiereDetails.setNom((String) requestBody.get("nom"));
        filiereDetails.setResponsable((String) requestBody.get("responsable"));
        // Ajoutez d'autres champs si nécessaire

        boolean updated = filiereService.updateFiliere(sigle, filiereDetails);
        if (updated) {
            return ResponseEntity.ok("{\"message\":\"Filière modifiée avec succès.\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Filière non trouvée.\"}");
    }



    @GetMapping("/toutes")
    public ResponseEntity<Iterable<Filiere>> getAllFilieres() {
        return ResponseEntity.ok(filiereService.getAllFilieres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable Long id) {
        return filiereService.getFiliereById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    } 
    
}



