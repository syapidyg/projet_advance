package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.FournisseurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.FournisseurResponseDto;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.repository.FournisseurRepository;
import com.advance.pharmacie.service.interfaces.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FournisseurImplementation implements FournisseurService {

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Override
    public FournisseurResponseDto createOrUpdate(FournisseurRequestDto dtoFournisseur) {

        if (Objects.nonNull(dtoFournisseur.getId()) && dtoFournisseur.getId() > 0) {

            Fournisseur fournisseur = fournisseurRepository.findById(dtoFournisseur.getId()).map(p -> {
                p.setAdress(dtoFournisseur.getAdress());
                p.setEmail(dtoFournisseur.getEmail());
                p.setName(dtoFournisseur.getName());
                p.setNumber(dtoFournisseur.getNumber());
                p.setCity(dtoFournisseur.getCity());

                return fournisseurRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucune fournisseur trouvé"));

            return FournisseurResponseDto.entityToDto(fournisseurRepository.save(fournisseur));
        } else {
            Fournisseur fournisseur = FournisseurRequestDto.dtoToEntity(dtoFournisseur);
            return FournisseurResponseDto.entityToDto(fournisseurRepository.save(fournisseur));
        }
    }

    @Override
    public List<FournisseurResponseDto> read() {

        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();

        return FournisseurResponseDto.entityToDtoList(fournisseurs);
    }

    @Override
    public FournisseurResponseDto readOne(Long id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucune caisse trouvé"));
        return FournisseurResponseDto.entityToDto(fournisseur);
    }

    @Override
    public String delete(Long id) {

        fournisseurRepository.deleteById(id);
        return "fournisseur supprimée avec succès";
    }
}
