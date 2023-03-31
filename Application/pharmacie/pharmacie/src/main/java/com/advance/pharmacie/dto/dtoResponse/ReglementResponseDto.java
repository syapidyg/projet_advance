package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.lnk.Reglement;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReglementResponseDto {


    private Long id;
    private UtilisateurResponseDto utilisateur;
    private CommandeResponseDto commande;
    private CaisseResponseDto caisse;
    private Long montant;
    private Long rendu;
    private Long reste;
    private LocalDateTime date;

    public static ReglementResponseDto entityToDto(Reglement reglement) {
        return ReglementResponseDto.builder()
                .id(reglement.getId())
                .utilisateur(UtilisateurResponseDto.entityToDto(reglement.getUtilisateur()))
                .caisse(CaisseResponseDto.entityToDto(reglement.getCaisse()))
                .commande(CommandeResponseDto.entityToDto(reglement.getCommande()))
                .montant(reglement.getMontant())
                .rendu(reglement.getRendu())
                .reste(reglement.getReste())
                .date(reglement.getDate_creation())
                .build();
    }

    public static List<ReglementResponseDto> entityToDtoList(List<Reglement> reglements) {
        return reglements.stream().map(ReglementResponseDto::entityToDto).collect(Collectors.toList());
    }

    public static Page<ReglementResponseDto> entityToDtoList(Page<Reglement> reglements){
        return reglements.map(ReglementResponseDto::entityToDto);
    }
}
