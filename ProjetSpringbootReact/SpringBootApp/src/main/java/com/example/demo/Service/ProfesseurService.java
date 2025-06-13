package com.example.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Compte;
import com.example.demo.Model.Element;
import com.example.demo.Model.Modalite;
import com.example.demo.Model.Professeur;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.ElementRepository;
import com.example.demo.repository.ModaliteRepository;
import com.example.demo.repository.ProfesseurRepository;

@Service
public class ProfesseurService {
	
    @Autowired
    private ModaliteRepository modaliteRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;


    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private CompteRepository compteRepository;
    // Créer un professeur
    public Professeur createProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur); // Sauvegarde du professeur
    }

    // Récupérer un professeur par son ID
    public Professeur getProfesseurById(Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id); // Recherche du professeur
        return professeur.orElse(null); // Si trouvé, retourne le professeur, sinon null
    }

    // Mettre à jour un professeur
    public Professeur updateProfesseur(Long id, Professeur professeur) {
        if (professeurRepository.existsById(id)) { // Vérifie si le professeur existe déjà
            professeur.setId(id); // On s'assure de garder l'ID actuel
            return professeurRepository.save(professeur); // Sauvegarde les modifications
        }
        return null; // Si le professeur n'existe pas, retourne null
    }

    // Supprimer un professeur par son ID
    public boolean deleteProfesseur(Long id) {
        if (professeurRepository.existsById(id)) { // Vérifie si le professeur existe
            professeurRepository.deleteById(id); // Supprime le professeur
            return true; // Retourne true si la suppression a réussi
        }
        return false; // Retourne false si le professeur n'existe pas
    }

    // Récupérer tous les professeurs
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll(); // Retourne la liste de tous les professeurs
    }
    public Map<String, Object> checkUsernamePassword(String username, String password) {
        Map<String, Object> response = new HashMap<>();

        // Vérifier le compte avec username et password
        Compte compte = compteRepository.checkUsernamePassword(username, password);

        if (compte != null) {
            System.out.println("ID extrait après authentification : " + compte.getId()); // Afficher l'ID extrait
            Optional<Professeur> professeurOpt = professeurRepository.findByCompteId(compte.getId()); // Utiliser la clé étrangère

            if (professeurOpt.isPresent()) {
                Professeur professeur = professeurOpt.get();
                response.put("isAuthenticated", true);
                response.put("professeur", professeur);
            } else {
                response.put("isAuthenticated", false);
                response.put("message", "Professeur non trouvé.");
            }
        } else {
            response.put("isAuthenticated", false);
            response.put("message", "Identifiants invalides.");
        }

        return response;
    }
    public Professeur ajouterElementSansSupprimer(Long professeurId, String intituleElement) {
        // Récupérer le professeur par son ID
        Professeur professeur = professeurRepository.findById(professeurId)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));

        List<Element> elementsExistants = professeur.getElements() != null ? professeur.getElements() : new ArrayList<>();

        // Vérifier si l'élément existe dans la base de données
        Element element = elementRepository.findByLabelleIgnoreCase(intituleElement)
                .orElseThrow(() -> new RuntimeException("Élément non trouvé : " + intituleElement));

        // Vérifier si l'élément est déjà dans la liste
        boolean dejaPresent = elementsExistants.stream()
                .anyMatch(e -> e.getId().equals(element.getId()));

        if (!dejaPresent) {
            elementsExistants.add(element);
        }

        professeur.setElements(elementsExistants);
        return professeurRepository.save(professeur);
    }


    public List<Element> getElementsProfesseur(Long idProfesseur) {
        Professeur professeur = professeurRepository.findById(idProfesseur)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));
        
        return professeur.getElements();
    }

    public void ajouterModalite(float tp, float examen, float projet, Element element) {
        // Vérifiez que l'élément n'est pas nul
        if (element == null) {
            throw new IllegalArgumentException("L'élément ne peut pas être nul.");
        }

        // Créer une nouvelle modalité
        Modalite modalite = new Modalite();
        modalite.setTp(tp);
        modalite.setExamen(examen);
        modalite.setProjet(projet);
        modalite.setElement(element); // Associer l'élément à la modalité

        // Enregistrer la modalité
        modaliteRepository.save(modalite); // Assurez-vous que le repository est bien injecté

        // Optionnel : Ajouter la modalité à la liste des modalités de l'élément
        element.addModalite(modalite);
    }



}

