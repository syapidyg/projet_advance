package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.FamilleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.FamilleResponseDto;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.repository.FamilleRepository;
import com.advance.pharmacie.service.interfaces.FamilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

public class FamilleImplementation implements FamilleService {

    @Autowired
    FamilleRepository familleRepository;


    @Override
    public FamilleResponseDto createOrUpdate(FamilleRequestDto dtoFamille) {

        if (Objects.nonNull(dtoFamille.getId()) && dtoFamille.getId() > 0) {

            Famille famille = familleRepository.findById(dtoFamille.getId()).map(p -> {
                p.setName(dtoFamille.getName());
                p.setRayon(dtoFamille.getRayon());
                p.setDescription(dtoFamille.getDescription());
                return familleRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucune famille trouvé"));

            return FamilleResponseDto.entityToDto(familleRepository.save(famille));
        } else {
            Famille famille = FamilleRequestDto.dtoToEntity(dtoFamille);
            return FamilleResponseDto.entityToDto(familleRepository.save(famille));
        }
    }

    @Override
    public List<FamilleResponseDto> read() {

        List<Famille> familles = familleRepository.findAll();

        return FamilleResponseDto.entityToDtoList(familles);
    }

    @Override
    public String delete(Long id) {

        familleRepository.deleteById(id);
        return "Famille supprimée avec succès";
    }

    @Override
    public FamilleResponseDto readOne(Long id) {
        Famille famille = familleRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucune famille trouvé"));
        return FamilleResponseDto.entityToDto(famille);
    }
}
