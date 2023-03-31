package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.ReglementRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ReglementResponseDto;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.model.lnk.Reglement;
import com.advance.pharmacie.repository.CaisseRepository;
import com.advance.pharmacie.repository.CommandeRepository;
import com.advance.pharmacie.repository.auth.UtilisateurRepository;
import com.advance.pharmacie.repository.lnk.ReglementRepository;
import com.advance.pharmacie.service.interfaces.lnk.ReglementService;
import com.advance.pharmacie.util.StatutCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ReglementImplementation implements ReglementService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    CaisseRepository caisseRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ReglementRepository reglementRepository;

    @Override
    public ReglementResponseDto createOrUpdate(ReglementRequestDto dtoReglement) {

        Utilisateur utilisateur = utilisateurRepository.findById(dtoReglement.getIdUtilisateur())
                .orElseThrow(() -> new RuntimeException("Utilisateur non renseigné"));

        Caisse caisse = caisseRepository.findById(dtoReglement.getIdCaisse())
                .orElseThrow(() -> new RuntimeException("Caisse non renseignée"));

        Commande commande = commandeRepository.findById(dtoReglement.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande non renseignée"));


        if (Objects.nonNull(dtoReglement.getId()) && dtoReglement.getId() > 0) {

            Reglement reglement = reglementRepository.findById(dtoReglement.getId()).map(p -> {
                p.setMontant(dtoReglement.getMontant());
                p.setRendu(dtoReglement.getRendu());
                p.setReste(dtoReglement.getReste());
                p.setCaisse(caisse);
                p.setUtilisateur(utilisateur);
                p.setCommande(commande);
                return reglementRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("Reglement introuvable")));

            return ReglementResponseDto.entityToDto(reglementRepository.save(reglement));
        }

        commande.setStatut(StatutCommande.REGLE);
        commandeRepository.save(commande);

        Reglement reglement = ReglementRequestDto.dtoToEntity(dtoReglement, utilisateur, caisse, commande);
        return ReglementResponseDto.entityToDto(reglementRepository.save(reglement));

    }

    @Override
    public Page<ReglementResponseDto> read(String token, Pageable pageable) {

        Page<Reglement> reglements = reglementRepository.findAllReglement(token, pageable);
        return ReglementResponseDto.entityToDtoList(reglements);
    }

    @Override
    public String delete(Long id) {

        reglementRepository.deleteById(id);
        return "Reglement suprimé avec succès";
    }

    @Override
    public Page<ReglementResponseDto> readFournisseur(String token, Pageable pageable) {
        Page<Reglement> reglements = reglementRepository.findAllReglementType("fournisseur", token, pageable);
        return ReglementResponseDto.entityToDtoList(reglements);
    }

    @Override
    public Page<ReglementResponseDto> readClient(String token, Pageable pageable) {
        Page<Reglement> reglements = reglementRepository.findAllReglementType("client", token, pageable);
        return ReglementResponseDto.entityToDtoList(reglements);
    }

    @Override
    public ReglementResponseDto readOne(Long id) {
        Reglement reglement = reglementRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun Reglement ne correspond a cet ID"));
        ;
        return ReglementResponseDto.entityToDto(reglement);
    }


}
