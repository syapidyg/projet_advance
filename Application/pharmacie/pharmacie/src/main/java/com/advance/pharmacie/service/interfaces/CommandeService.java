package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.BonToFactureRequestDto;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommandeService {

    CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCaisse);

    Page<CommandeResponseDto> read(String type, String token, Pageable pageable);

    CommandeResponseDto readOne(Long id);

//    Page<CommandeResponseDto> readStock(String type, String document, String dateDebut, String dateFin, String token, Pageable pageable);

    Page<CommandeResponseDto> readStock(String type, String token, Pageable pageable);

    Page<CommandeResponseDto> readByType(String type, String document, String dateDebut, String dateFin, String token, Pageable pageable, int sizeOf);

    String delete(Long id);

    CommandeResponseDto tBonFacture(BonToFactureRequestDto dto);

//    Page<CommandeResponseDto> readClientOrFournisseur(String type, String token, Pageable pageable);

    Page<CommandeResponseDto> readClient(String type, String document, String token, Pageable pageable);

    Page<CommandeResponseDto> readFournisseur(String type, String token, Pageable pageable);

//    Page<CommandeResponseDto> readStock(String token, Pageable pageable);
}
