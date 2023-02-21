package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Reglement;
import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class ReglementRequestDto {


    private Long id;
    private Utilisateur utilisateur;
    private Long idCommande;
    private Long idCaisse;

    public Reglement dtoToEntity(ReglementRequestDto dto, Utilisateur utilisateur, Caisse caisse, Commande commande) {
        return Reglement.ReglementBuilder.aReglement()
                .id(dto.getId())
                .utilisateur(utilisateur)
                .caisse(caisse)
                .commande(commande)
                .build();
    }

}
