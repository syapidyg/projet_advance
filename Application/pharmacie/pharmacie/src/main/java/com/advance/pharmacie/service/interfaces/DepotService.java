package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoRequest.DepotRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.DepotResponseDto;

import java.util.List;

public interface DepotService {

    DepotResponseDto createOrUpdate(DepotRequestDto dtoDepot);

    List<DepotResponseDto> read();

    String delete(Long id);

    DepotResponseDto readOne(Long id);
}
