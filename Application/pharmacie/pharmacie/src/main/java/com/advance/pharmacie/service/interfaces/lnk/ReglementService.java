package com.advance.pharmacie.service.interfaces.lnk;

import com.advance.pharmacie.dto.dtoRequest.ReglementRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ReglementResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReglementService {

    ReglementResponseDto createOrUpdate(ReglementRequestDto dtoReglement);

    Page<ReglementResponseDto> read(String token, Pageable pageable);

    ReglementResponseDto readOne(Long id);

    String delete(Long id);

    Page<ReglementResponseDto> readFournisseur(String token, Pageable pageable);

    Page<ReglementResponseDto> readClient(String token, Pageable pageable);
}
