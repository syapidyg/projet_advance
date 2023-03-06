package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.EmployeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.EmployeResponseDto;
import com.advance.pharmacie.model.Employe;
import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.repository.EmployeRepository;
import com.advance.pharmacie.repository.auth.UtilisateurRepository;
import com.advance.pharmacie.service.interfaces.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class EmployeImplementation implements EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public EmployeResponseDto createOrUpdate(EmployeRequestDto dtoEmploye) {

        Utilisateur utilisateur = utilisateurRepository.findById(dtoEmploye.getIdUtilisateur())
                .orElseThrow(()-> new IllegalStateException("Utiisateur non existant"));

            if (Objects.nonNull(dtoEmploye.getId()) && dtoEmploye.getId() > 0) {
                Employe employe = employeRepository.findById(dtoEmploye.getId()).map(p -> {
                    p.setNom(dtoEmploye.getNom());
                    p.setBirthday(dtoEmploye.getBirthday());
                    p.setPrenom(dtoEmploye.getPrenom());
                    p.setEmail(dtoEmploye.getEmail());
                    p.setEmail(dtoEmploye.getEmail());
                    p.setUtilisateur(utilisateur);
                    return employeRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Aucune employe trouvé"));

                return EmployeResponseDto.entityToDto(employeRepository.save(employe));
            }
            Employe employe = employeRepository.save(EmployeRequestDto.dtoToEntity(dtoEmploye, utilisateur));
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
