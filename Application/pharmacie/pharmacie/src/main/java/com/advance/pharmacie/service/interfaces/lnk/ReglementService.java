package com.advance.pharmacie.service.interfaces.lnk;

import com.advance.pharmacie.dto.dtoRequest.ReglementRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ReglementResponseDto;

import java.util.List;

public interface ReglementService {

    ReglementResponseDto createOrUpdate(ReglementRequestDto dtoReglement);

    List<ReglementResponseDto> read();

    ReglementResponseDto readOne(Long id);

    String delete(Long id);
}
