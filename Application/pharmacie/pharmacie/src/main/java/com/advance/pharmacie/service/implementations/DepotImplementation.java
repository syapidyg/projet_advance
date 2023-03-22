package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.DepotRequestDto;
import com.advance.pharmacie.dto.dtoResponse.DepotResponseDto;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.repository.DepotRepository;
import com.advance.pharmacie.service.interfaces.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepotImplementation implements DepotService {

@Autowired
    DepotRepository depotRepository;

    @Override
    public DepotResponseDto createOrUpdate(DepotRequestDto dtoDepot) {

        if (Objects.nonNull(dtoDepot.getId()) && dtoDepot.getId() > 0) {

            Depot depot = depotRepository.findById(dtoDepot.getId()).map(p -> {
                p.setName(dtoDepot.getName());
                p.setDescription(dtoDepot.getDescription());
                return depotRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucun Depot trouvé"));

            return DepotResponseDto.entityToDto(depotRepository.save(depot));
        } else {
            Depot depot = DepotRequestDto.dtoToEntity(dtoDepot);
            return DepotResponseDto.entityToDto(depotRepository.save(depot));
        }
    }

    @Override
    public List<DepotResponseDto> read() {
        List<Depot> depots = depotRepository.findAll();
        return DepotResponseDto.entityToDtoList(depots);
    }

    @Override
    public String delete(Long id) {
        depotRepository.deleteById(id);
        return "Depot supprimé avec succès";
    }

    @Override
    public DepotResponseDto readOne(Long id) {
        Depot depot = depotRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun depot trouvé"));
        return DepotResponseDto.entityToDto(depot);
    }
}
