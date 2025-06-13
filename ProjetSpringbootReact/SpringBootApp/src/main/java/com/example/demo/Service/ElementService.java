package com.example.demo.Service;

import com.example.demo.EtudiantDto;
import com.example.demo.StudentNoteDto;
import com.example.demo.Model.Element;
import com.example.demo.Model.ElementEtudiant;
import com.example.demo.Model.Etudiant;
import com.example.demo.Model.EtudiantDTO;
import com.example.demo.Model.Modalite;
import com.example.demo.Model.Professeur;
import com.example.demo.Model.StudentDto;
import com.example.demo.repository.ElementEtudiantRepository;
import com.example.demo.repository.ElementRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.ModaliteRepository;
import com.example.demo.repository.ProfesseurRepository;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.persistence.EntityNotFoundException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ElementService {
    @Autowired
    private ModaliteRepository  modaliteRepository;

    private final ElementRepository elementRepository;
    private final ElementEtudiantRepository elementEtudiantRepository;
    private final EtudiantRepository etudiantRepository;
    private final ProfesseurRepository prprofesseurrepository;


    public ElementService(ElementRepository elementRepository, ElementEtudiantRepository elementEtudiantRepository, 
    		EtudiantRepository etudiantRepository,ProfesseurRepository prprofesseurrepository) {
        this.elementRepository = elementRepository;
        this.elementEtudiantRepository = elementEtudiantRepository;
        this.etudiantRepository = etudiantRepository;
		this.prprofesseurrepository = prprofesseurrepository;
    }
    public List<Element> findElementsValidesByProfesseur(Long profId) {
        // Vérifier si le professeur existe
        Professeur professeur = prprofesseurrepository.findById(profId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professeur non trouvé"));

        // Récupérer les éléments validés associés au professeur
        return elementRepository.findByProfesseursAndIsValideTrue(professeur);
    }
    public List<Element> findElementsNValidesByProfesseur(Long profId) {
        // Vérifier si le professeur existe
        Professeur professeur = prprofesseurrepository.findById(profId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professeur non trouvé"));

        // Récupérer les éléments validés associés au professeur
        return elementRepository.findByProfesseursAndIsValideFalse(professeur);
    }
    public List<Element> findAllElementsValides() {
        // Récupérer tous les éléments validés
        return elementRepository.findByIsValideTrue();
    }

    public byte[] exportNotesToPDF(Long elementId) throws IOException, DocumentException {
        // Vérifier si l'élément est validé
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé"));

        if (Boolean.FALSE.equals(element.getIsValide())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'élément n'est pas validé");
        }

        // Récupérer les étudiants et leurs notes
        List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);

        // Initialisation du document PDF
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Ajouter un titre
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Exportation des Notes - Élément: " + element.getLabelle(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Création d'un tableau PDF
        PdfPTable table = new PdfPTable(5); // 5 colonnes
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        // Définir les en-têtes
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        table.addCell(new PdfPCell(new Phrase("Nom", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Prénom", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Note", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Validation", headerFont)));
        table.addCell(new PdfPCell(new Phrase("Absence", headerFont)));

        // Ajouter les données
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
        for (ElementEtudiant elementEtudiant : elementEtudiants) {
            table.addCell(new PdfPCell(new Phrase(elementEtudiant.getEtudiant().getNom(), dataFont))); // Nom
            table.addCell(new PdfPCell(new Phrase(elementEtudiant.getEtudiant().getPrenom(), dataFont))); // Prénom
            table.addCell(new PdfPCell(new Phrase(String.valueOf(elementEtudiant.getNote()), dataFont)));
            table.addCell(new PdfPCell(new Phrase(elementEtudiant.getIsValide() ? "Valide" : "Non valide", dataFont)));
            table.addCell(new PdfPCell(new Phrase(elementEtudiant.getIsAbsent() ? "Absent" : "Présent", dataFont)));
        }

        // Ajouter le tableau au document
        document.add(table);

        // Fermer le document
        document.close();

        return baos.toByteArray();
    }


    public byte[] exportNotesToExcel(Long elementId) throws IOException {
        // Vérifier si l'élément est validé
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé"));

        if (Boolean.FALSE.equals(element.getIsValide())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'élément n'est pas validé");
        }

        // Récupérer les étudiants et leurs notes
        List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);

        // Création du fichier Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Notes");

        // Création des en-têtes
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom");
        headerRow.createCell(1).setCellValue("Prénom");
        headerRow.createCell(2).setCellValue("Note");
        headerRow.createCell(3).setCellValue("Validation");
        headerRow.createCell(4).setCellValue("Absence");

        // Remplir les données
        int rowNum = 1;
        for (ElementEtudiant elementEtudiant : elementEtudiants) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(elementEtudiant.getEtudiant().getNom()); // Nom de l'étudiant
            row.createCell(1).setCellValue(elementEtudiant.getEtudiant().getPrenom()); // Prénom de l'étudiant
            row.createCell(2).setCellValue(elementEtudiant.getNote());
            row.createCell(3).setCellValue(elementEtudiant.getIsValide() ? "Valide" : "Non valide");
            row.createCell(4).setCellValue(elementEtudiant.getIsAbsent() ? "Absent" : "Présent");
        }

        // Écrire dans le byte array output stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    public void saisirNotes(Long elementId, List<Map<String, Object>> notesAbsences) {
        // Récupérer l'élément avec l'ID
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new EntityNotFoundException("Élément non trouvé avec l'ID " + elementId));

        // Si l'élément est déjà validé, on empêche les mises à jour
        if (Boolean.TRUE.equals(element.getIsValide())) {
            throw new IllegalStateException("L'élément est déjà validé. Les notes ne peuvent pas être modifiées.");
        }

        // Liste pour stocker les résultats
        List<StudentDto> etudiantDtos = new ArrayList<>();

        // Récupérer la modalité associée à l'élément
        Modalite modalite = modaliteRepository.findByElementId(elementId);

        // Boucle sur la liste des étudiants pour saisir les notes et absences
        for (Map<String, Object> noteAbsenceMap : notesAbsences) {
            Long etudiantId = Long.valueOf(noteAbsenceMap.get("etudiantId").toString());
            Double note = noteAbsenceMap.get("note") != null ? Double.valueOf(noteAbsenceMap.get("note").toString()) : null;
            Double noteTP = noteAbsenceMap.get("noteTP") != null ? Double.valueOf(noteAbsenceMap.get("noteTP").toString()) : null;
            Double noteProjet = noteAbsenceMap.get("noteProjet") != null ? Double.valueOf(noteAbsenceMap.get("noteProjet").toString()) : null;
            Boolean isAbsent = noteAbsenceMap.get("isAbsent") != null ? Boolean.valueOf(noteAbsenceMap.get("isAbsent").toString()) : false;

            // Vérifier si l'étudiant existe
            Etudiant etudiant = etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID " + etudiantId));

            // Vérifier si l'étudiant est inscrit dans cet élément
            ElementEtudiant elementEtudiant = elementEtudiantRepository.findByElementIdAndEtudiantCne(elementId, etudiantId);
            if (elementEtudiant == null) {
                throw new EntityNotFoundException("Relation Étudiant-Élément non trouvée pour l'étudiant avec CNE " + etudiantId);
            }

            // Vérification de la validité des notes
            if ((note != null && (note < 0 || note > 20)) ||
                (noteTP != null && (noteTP < 0 || noteTP > 20)) ||
                (noteProjet != null && (noteProjet < 0 || noteProjet > 20))) {
                throw new IllegalArgumentException("Les notes doivent être comprises entre 0 et 20 pour l'étudiant avec CNE " + etudiantId);
            }

            // Calcul de la note finale avec modalité en pourcentage
            double noteFinale = (note != null ? note * (modalite.getExamen() / 100.0) : 0) +
                                (noteTP != null ? noteTP * (modalite.getTp() / 100.0) : 0) +
                                (noteProjet != null ? noteProjet * (modalite.getProjet() / 100.0) : 0);

            // Mise à jour des données
            elementEtudiant.setNote(note);
            elementEtudiant.setNoteTp(noteTP);
            elementEtudiant.setNoteProjet(noteProjet);
            elementEtudiant.setIsAbsent(isAbsent);
            elementEtudiant.setIsValide(noteFinale >= 10); // Validation automatique selon la note finale
            elementEtudiant.setNoteExamen(noteFinale); // Mise à jour de la note finale

            // Sauvegarde des modifications
            elementEtudiantRepository.save(elementEtudiant);

            // Création de l'objet DTO
            StudentDto etudiantDto = new StudentDto();
            etudiantDto.setCne(etudiant.getCne());
            etudiantDto.setNom(etudiant.getNom());
            etudiantDto.setPrenom(etudiant.getPrenom());
            etudiantDto.setNote(note);
            etudiantDto.setNoteTP(noteTP);
            etudiantDto.setNoteProjet(noteProjet);
            etudiantDto.setNoteFinale(noteFinale);
            etudiantDto.setIsValide(elementEtudiant.getIsValide());

            // Ajout à la liste des DTO
            etudiantDtos.add(etudiantDto);
        }

        // Retourner ou traiter la liste des DTO si nécessaire
    }

    public void validerElement(Long elementId, Boolean isValide) {
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new EntityNotFoundException("Élément non trouvé avec l'ID " + elementId));

        // Met à jour le champ isValide
        element.setIsValide(isValide);
        elementRepository.save(element);
    }


    public List<String> getEtudiantsByElementId(Long elementId) {
        // Récupérer l'élément à partir de l'ID
        Element element = elementRepository.findById(elementId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé avec l'ID " + elementId));

        // Vérifier que l'élément n'est pas encore validé
        if (Boolean.TRUE.equals(element.getIsValide())) {
            throw new IllegalStateException("L'élément est déjà validé. Les notes ne peuvent pas être modifiées.");
        }

        // Récupérer les étudiants associés à l'élément
        List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);

        // Extraire les noms et prénoms des étudiants
        return elementEtudiants.stream()
            .map(ee -> ee.getEtudiant().getNom() + " " + ee.getEtudiant().getPrenom())
            .collect(Collectors.toList());
    }

    public List<String> getEtudiantsByElementIdv(Long elementId) {
        // Récupérer l'élément à partir de l'ID
        Element element = elementRepository.findById(elementId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé avec l'ID " + elementId));

        // Vérifier que l'élément est validé
        if (!Boolean.TRUE.equals(element.getIsValide())) {
            throw new IllegalStateException("L'élément n'est pas encore validé. Les étudiants ne peuvent pas être récupérés.");
        }

        // Récupérer les étudiants associés à l'élément
        List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);

        // Extraire les noms et prénoms des étudiants
        return elementEtudiants.stream()
            .map(ee -> ee.getEtudiant().getNom() + " " + ee.getEtudiant().getPrenom())
          .collect(Collectors.toList());
    }

    public List<StudentNoteDto> getStudentsNotes(Long elementId) {
        // Vérifier si l'élément est validé
        Element element = elementRepository.findById(elementId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé"));

        if (Boolean.FALSE.equals(element.getIsValide())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'élément n'est pas validé");
        }

        // Récupérer les étudiants et leurs notes
        List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);
        List<StudentNoteDto> studentNotes = new ArrayList<>();

        // Remplir les données dans un format DTO
        for (ElementEtudiant elementEtudiant : elementEtudiants) {
            StudentNoteDto dto = new StudentNoteDto();
            dto.setNom(elementEtudiant.getEtudiant().getNom());
            dto.setPrenom(elementEtudiant.getEtudiant().getPrenom());
            dto.setNote(elementEtudiant.getNote());
            dto.setIsValide(elementEtudiant.getIsValide());
            dto.setIsAbsent(elementEtudiant.getIsAbsent());
            studentNotes.add(dto);
        }

        return studentNotes;



}  public List<EtudiantDTO> getEtudiantsByElementId2(Long elementId) {
    // Récupérer l'élément à partir de l'ID
    Element element = elementRepository.findById(elementId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé avec l'ID " + elementId));

    // Vérifier que l'élément n'est pas encore validé
    if (Boolean.TRUE.equals(element.getIsValide())) {
        throw new IllegalStateException("L'élément est déjà validé. Les notes ne peuvent pas être modifiées.");
    }

    // Récupérer les étudiants associés à l'élément
    List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);

    // Extraire les noms, prénoms et CNE des étudiants
    return elementEtudiants.stream()
        .map(ee -> {
            String nom = ee.getEtudiant().getNom();
            String prenom = ee.getEtudiant().getPrenom();
            Long cne = ee.getEtudiant().getCne(); // Assurez-vous que cette méthode existe
            return new EtudiantDTO(nom, prenom, cne);
        })
        .collect(Collectors.toList());
}
public List<StudentDto> getStudentsNotes2(Long elementId) {
    // Récupérer l'élément sans vérifier sa validité
    Element element = elementRepository.findById(elementId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Élément non trouvé"));

    // Récupérer les étudiants et leurs notes
    List<ElementEtudiant> elementEtudiants = elementEtudiantRepository.findByElement(element);
    List<StudentDto> studentNotes = new ArrayList<>();

    // Remplir les données dans un format DTO
    for (ElementEtudiant elementEtudiant : elementEtudiants) {
        StudentDto dto = new StudentDto();
        dto.setCne(elementEtudiant.getEtudiant().getCne()); // Ajout du CNE
        dto.setNom(elementEtudiant.getEtudiant().getNom());
        dto.setPrenom(elementEtudiant.getEtudiant().getPrenom());
        dto.setNote(elementEtudiant.getNoteExamen());
        dto.setNoteTP(elementEtudiant.getNoteTp()); // Note de TP
        dto.setNoteProjet(elementEtudiant.getNoteProjet()); // Note de projet
    // Calcul de la note finale
        dto.setIsValide(elementEtudiant.getIsValide());
        studentNotes.add(dto);
    }

    return studentNotes;
}




    }
