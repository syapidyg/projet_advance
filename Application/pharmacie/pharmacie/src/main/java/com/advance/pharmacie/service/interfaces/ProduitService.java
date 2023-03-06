package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;

import java.util.List;

public interface ProduitService {

    ProduitResponseDto createOrUpdate(ProduitRequestDto dtoProduit);

    List<ProduitResponseDto> read();

    String delete(Long id);

    ProduitResponseDto readOne(Long id);


}
