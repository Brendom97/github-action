package com.gloire.githubaction.controller;

import com.gloire.githubaction.dto.EtudiantDTO;
import com.gloire.githubaction.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public ResponseEntity<String> createEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        etudiantService.create(etudiantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Etudiant créé avec succès");
    }

    @GetMapping
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        List<EtudiantDTO> etudiants = etudiantService.read();
        return ResponseEntity.ok(etudiants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable int id) {
        EtudiantDTO etudiantDTO = etudiantService.lire(id);
        if (etudiantDTO != null) {
            return ResponseEntity.ok(etudiantDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEtudiant(@PathVariable int id, @RequestBody EtudiantDTO etudiantDTO) {
        etudiantService.update(id, etudiantDTO);
        return ResponseEntity.ok("Etudiant mis à jour avec succès");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable int id) {
        etudiantService.delete(id);
        return ResponseEntity.ok("Etudiant supprimé avec succès");
    }
}

