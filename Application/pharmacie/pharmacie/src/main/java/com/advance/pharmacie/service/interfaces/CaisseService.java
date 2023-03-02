package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;

import java.util.List;

public interface CaisseService {

    CaisseResponseDto createOrUpdate(CaisseRequestDto dtoCaisse);

    List<CaisseResponseDto> read();

    CaisseResponseDto readOne(Long id);

    String delete(Long id);


}
