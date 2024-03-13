package com.gloire.githubaction.service;

import com.gloire.githubaction.dto.EtudiantDTO;
import com.gloire.githubaction.entity.Etudiant;
import com.gloire.githubaction.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Autowired
    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void create(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = convertToEntity(etudiantDTO);
        etudiantRepository.save(etudiant);
    }

    public List<EtudiantDTO> read() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiants.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EtudiantDTO lire(int id) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        return optionalEtudiant.map(this::convertToDto).orElse(null);
    }

    public void update(int id, EtudiantDTO etudiantDTO) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
        if (optionalEtudiant.isPresent()) {
            Etudiant etudiantDansBDD = optionalEtudiant.get();
            etudiantDansBDD.setNom(etudiantDTO.getNom());
            etudiantDansBDD.setPrenom(etudiantDTO.getPrenom());
            etudiantDansBDD.setEmail(etudiantDTO.getEmail());
            etudiantDansBDD.setTelephone(etudiantDTO.getTelephone());
            etudiantRepository.save(etudiantDansBDD);
        }
    }

    public void delete(int id) {
        etudiantRepository.deleteById(id);
    }

    private Etudiant convertToEntity(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setTelephone(etudiantDTO.getTelephone());
        return etudiant;
    }

    private EtudiantDTO convertToDto(Etudiant etudiant) {
        return new EtudiantDTO(
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getEmail(),
                etudiant.getTelephone()
        );
    }
}

