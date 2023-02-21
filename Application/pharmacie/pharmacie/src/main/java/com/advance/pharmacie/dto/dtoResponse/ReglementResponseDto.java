package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Reglement;
import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReglementResponseDto {


    private Long id;
    private UtilisateurResponseDto utilisateur;
    private CommandeResponseDto commande;
    private CaisseResponseDto caisse;

    public ReglementResponseDto entityToDto(Reglement reglement) {
        return ReglementResponseDto.builder()
                .id(reglement.getId())
                .utilisateur(UtilisateurResponseDto.entityToDto(reglement.getUtilisateur()))
                .caisse(CaisseResponseDto.entityToDto(reglement.getCaisse()))
                .commande(CommandeResponseDto.entityToDto(reglement.getCommande()))
                .build();
    }

}
