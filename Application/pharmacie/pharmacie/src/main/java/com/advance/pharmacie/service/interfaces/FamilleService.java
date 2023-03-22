package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.DepotRequestDto;
import com.advance.pharmacie.dto.dtoRequest.FamilleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.DepotResponseDto;
import com.advance.pharmacie.dto.dtoResponse.FamilleResponseDto;

import java.util.List;

public interface FamilleService {

    FamilleResponseDto createOrUpdate(FamilleRequestDto dtoFamille);

    List<FamilleResponseDto> read();

    String delete(Long id);

    FamilleResponseDto readOne(Long id);
}

