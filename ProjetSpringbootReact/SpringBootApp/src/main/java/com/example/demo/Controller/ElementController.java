package com.example.demo.Controller;

import com.example.demo.Model.Element;
import com.example.demo.Model.ElementEtudiant;
import com.example.demo.Service.ElementService;
import com.itextpdf.text.DocumentException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ElementController {
    public static final MediaType APPLICATION_PDF = MediaType.parseMediaType("application/pdf");
 
    private final ElementService elementService;

    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }
    
    @GetMapping("/valides")
    public ResponseEntity<List<Element>> getAllElementsValides() {
        List<Element> elementsValides = elementService.findAllElementsValides();
        return new ResponseEntity<>(elementsValides, HttpStatus.OK);
    }

    @GetMapping("/professeur/{profId}/elements-valides")
    public ResponseEntity<List<Element>> getElementsValidesByProfesseur(@PathVariable Long profId) {
        List<Element> elementsValides = elementService.findElementsValidesByProfesseur(profId);
        return ResponseEntity.ok(elementsValides);
    }

    @GetMapping("/professeur/{profId}/elements-Nonvalides")
    public ResponseEntity<List<Element>> getElementsNValidesByProfesseur(@PathVariable Long profId) {
        List<Element> elementsValides = elementService.findElementsNValidesByProfesseur(profId);
        return ResponseEntity.ok(elementsValides);
    }

    @PostMapping("/excelnotes")
    public ResponseEntity<byte[]> exportNotes(@RequestBody Map<String, Long> requestBody) {
        Long elementId = requestBody.get("elementId");
        try {
            byte[] excelData = elementService.exportNotesToExcel(elementId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=notes_" + elementId + ".xlsx");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Erreur lors de la génération du fichier Excel : " + e.getMessage()).getBytes());
        }
    }
    
    @PostMapping("/pdfnotes")
    public ResponseEntity<byte[]> getEtudiantsByElementId(@RequestBody Map<String, Long> requestBody) {
        Long elementId = requestBody.get("elementId");
        if (elementId == null) {
            return ResponseEntity.badRequest()
                    .body(("ID de l'élément manquant dans la requête").getBytes());
        }

        try {
            byte[] pdfContent = elementService.exportNotesToPDF(elementId);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"notes_element.pdf\"")
                    .body(pdfContent);
        } catch (IOException | DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Erreur lors de la génération du fichier PDF : " + e.getMessage()).getBytes());
        }
    }
}

