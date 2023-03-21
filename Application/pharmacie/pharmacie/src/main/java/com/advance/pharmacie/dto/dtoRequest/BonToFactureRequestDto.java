package com.advance.pharmacie.dto.dtoRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class  BonToFactureRequestDto {

    Long idCommande;
    Long idDepot;

}
