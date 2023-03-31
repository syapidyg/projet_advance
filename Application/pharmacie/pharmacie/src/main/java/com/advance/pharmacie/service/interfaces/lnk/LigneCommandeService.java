package com.advance.pharmacie.service.interfaces.lnk;

import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.LigneCommandeResponseDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeResponseDto createOrUpdate(LigneCommandeRequestDto dtoReglement);

    List<LigneCommandeResponseDto> read();

    LigneCommandeResponseDto readOne(Long id);

    String delete(Long id);

    List<LigneCommandeResponseDto> readClient(Long id);

    List<LigneCommandeResponseDto> readFournisseur(Long id);

    List<LigneCommandeResponseDto> readStock(Long id);
}
