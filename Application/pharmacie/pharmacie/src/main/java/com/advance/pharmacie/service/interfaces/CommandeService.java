package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.BonToFactureRequestDto;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;

import java.util.List;

public interface CommandeService {

    CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCaisse);

    List<CommandeResponseDto> read();

    CommandeResponseDto readOne(Long id);

    List<CommandeResponseDto> readClient();

    List<CommandeResponseDto> readFournisseur();

    String delete(Long id);

    CommandeResponseDto tBonFacture(BonToFactureRequestDto dto);
}
