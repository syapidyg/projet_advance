package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.repository.CaisseRepository;
import com.advance.pharmacie.service.interfaces.CaisseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class CaisseImplementation implements CaisseService {

    @Autowired
    CaisseRepository caisseRepository;

    //Fonction de creation ou de mise a jour des informations liées a la caisse
    @Override
    public CaisseResponseDto createOrUpdate(CaisseRequestDto dtoCaisse) {

        if (Objects.nonNull(dtoCaisse.getId()) && dtoCaisse.getId() > 0) {
            Caisse caisse = caisseRepository.findById(dtoCaisse.getId()).map(p -> {
                p.setName(dtoCaisse.getName());
                p.setDescription(dtoCaisse.getDescription());
                return caisseRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucune caisse trouvé"));

            return CaisseResponseDto.entityToDto(caisseRepository.save(caisse));
        }
        Caisse caisse = caisseRepository.save(CaisseRequestDto.dtoToEntity(dtoCaisse));
        return CaisseResponseDto.entityToDto(caisse);
    }

    @Override
    public List<CaisseResponseDto> read() {
        return CaisseResponseDto.entityToDtoList(caisseRepository.findAll());
    }

    @Override
    public CaisseResponseDto readOne(Long id) {
        Caisse caisse = caisseRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucune caisse trouvé"));
        return CaisseResponseDto.entityToDto(caisse);
    }

    @Override
    public String delete(Long id) {
        caisseRepository.deleteById(id);
        return "Caisse supprimée avec succes";
    }
}
