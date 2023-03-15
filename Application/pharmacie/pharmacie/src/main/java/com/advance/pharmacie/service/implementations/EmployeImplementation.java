package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.EmployeRequestDto;
import com.advance.pharmacie.dto.dtoRequest.EmployeUtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoRequest.UtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.EmployeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;
import com.advance.pharmacie.model.Employe;
import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.repository.EmployeRepository;
import com.advance.pharmacie.repository.auth.UtilisateurRepository;
import com.advance.pharmacie.service.Auth.UtilisateurService;
import com.advance.pharmacie.service.interfaces.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeImplementation implements EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public EmployeResponseDto createOrUpdate(EmployeUtilisateurRequestDto dtoEmploye) {

        if (Objects.nonNull(dtoEmploye.getId()) && dtoEmploye.getId() > 0) {
            Employe employe = employeRepository.findById(dtoEmploye.getId()).map(p -> {
                p.setNom(dtoEmploye.getNom());
                p.setBirthday(dtoEmploye.getBirthday());
                p.setPrenom(dtoEmploye.getPrenom());
                p.setEmail(dtoEmploye.getEmail());
                p.setNumber(dtoEmploye.getNumber());
//                p.setUtilisateur(utilisateur);
                return employeRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucune employe trouvé"));

            return EmployeResponseDto.entityToDto(employeRepository.save(employe));
        }

        if (utilisateurRepository.existsByUsername(dtoEmploye.getUsername())) {
            throw new RuntimeException("Cet Username existe deja");
        }

        Employe employe = employeRepository
                .save(EmployeUtilisateurRequestDto
                        .dtoToEntityEmploye(dtoEmploye));

        Employe employeToSave = employeRepository
                .findById(employe.getId())
                .orElseThrow(() -> new RuntimeException("Erreur dans l'echange utilisateur client"));

        Utilisateur utilisateur = utilisateurRepository
                .save(EmployeUtilisateurRequestDto
                        .dtoToEntityUtilisateur(dtoEmploye, employeToSave));

        return EmployeResponseDto.entityToDto(employe);
    }

    @Override
    public List<EmployeResponseDto> read() {
        return EmployeResponseDto.entityToDtoList(employeRepository.findAll());
    }

    @Override
    public EmployeResponseDto readOne(Long id) {
        Employe employe = employeRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucune employe trouvé"));
        return EmployeResponseDto.entityToDto(employe);
    }

    @Override
    public String delete(Long id) {
        employeRepository.deleteById(id);
        return "employe supprimée avec succes";
    }
}
