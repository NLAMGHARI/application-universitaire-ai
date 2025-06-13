package com.example.demo.Service;

import com.example.demo.Model.Element;
import com.example.demo.Model.ElementDetailDTO;
import com.example.demo.Model.Modalite;
import com.example.demo.Model.ModaliteDTO;
import com.example.demo.Model.Module;
import com.example.demo.Model.Professeur;
import com.example.demo.Model.ProfesseurDetailDTO;
import com.example.demo.repository.ElementRepository;
import com.example.demo.repository.ModaliteRepository;
import com.example.demo.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ElementRepository elementRepository;
 // Pour accéder aux modules

    @Autowired
    private ModaliteRepository modaliteRepository; // Pour accéder aux modalités

    public ResponseEntity<String> ajouterElementDansModule(Long moduleId, Element element) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new FileSystemNotFoundException("Module non trouvé avec l'ID : " + moduleId));

        if (module.getElements() != null && module.getElements().size() >= 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ce module contient déjà 2 éléments. Impossible d'en ajouter un autre.");
        }

        element.setModule(module);
        elementRepository.save(element);

        return ResponseEntity.ok("Élément ajouté avec succès.");
    }

    public List<Element> recupererElements(Long moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module non trouvé avec l'ID : " + moduleId));

        return module.getElements();
    }

    public String supprimerElement(Long elementId) {
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new RuntimeException("Élément non trouvé avec l'ID : " + elementId));

        elementRepository.delete(element);
        return "Élément supprimé avec succès.";
    }

    public void save(Module nouveauModule) {
        moduleRepository.save(nouveauModule);
    }
    public List<ElementDetailDTO> getDetailsElementsDuModule(Long moduleId) {
        // Récupérer le module par son ID
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module non trouvé"));

        // Récupérer les éléments associés au module
        List<Element> elements = module.getElements();

        // Créer une liste pour stocker les DTO
        List<ElementDetailDTO> elementDetailDTOs = new ArrayList<>();

        for (Element element : elements) {
            ElementDetailDTO dto = new ElementDetailDTO();
            dto.setNomElement(element.getLabelle());

            // Récupérer les professeurs associés à l'élément
            List<Professeur> professeurs = element.getProfesseurs();
            List<ProfesseurDetailDTO> professeurDTOs = new ArrayList<>();

            for (Professeur professeur : professeurs) {
                ProfesseurDetailDTO professeurDTO = new ProfesseurDetailDTO();
                professeurDTO.setNom(professeur.getNom());
                professeurDTO.setPrenom(professeur.getPrenom());
                professeurDTOs.add(professeurDTO);
            }

            dto.setProfesseurs(professeurDTOs);

            // Récupérer la modalité associée à l'élément (une seule)
            Modalite modalite = modaliteRepository.findByElementId(element.getId());
            ModaliteDTO modaliteDTO = new ModaliteDTO();
            modaliteDTO.setTp(modalite.getTp());
            modaliteDTO.setExam(modalite.getExamen());
            modaliteDTO.setProjet(modalite.getProjet());

            dto.setModalite(modaliteDTO);
            elementDetailDTOs.add(dto);
        }

        return elementDetailDTOs;
    }
    // Méthode pour récupérer un module par son code
    public Optional<Module> getModuleByCode(String code) {
        return moduleRepository.findByCode(code);
    }

    // Méthode pour supprimer un module
    public void delete(Module module) {
        moduleRepository.delete(module);
    }

}
