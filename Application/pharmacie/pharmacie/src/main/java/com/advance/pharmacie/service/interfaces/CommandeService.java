package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;

import java.util.List;

public interface CommandeService {

    CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCaisse);

    List<CommandeResponseDto> read();

    CommandeResponseDto readOne(Long id);

    String delete(Long id);
}
