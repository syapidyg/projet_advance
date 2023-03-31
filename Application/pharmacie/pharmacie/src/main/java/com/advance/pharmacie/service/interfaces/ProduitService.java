package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProduitService {

    ProduitResponseDto createOrUpdate(ProduitRequestDto dtoProduit);

    Page<ProduitResponseDto> read(String token, Pageable pageable);

    String delete(Long id);

    ProduitResponseDto readOne(Long id);


}
