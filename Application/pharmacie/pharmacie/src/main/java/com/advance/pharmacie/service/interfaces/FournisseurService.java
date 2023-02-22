package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.FournisseurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.FournisseurResponseDto;

import java.util.List;

public interface FournisseurService {

    FournisseurResponseDto createOrUpdate(FournisseurRequestDto dtoFournisseur);

    List<FournisseurResponseDto> read();

    String delete(Long id);
}
