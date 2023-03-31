package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.ClientRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientResponseDto createOrUpdate(ClientRequestDto dtoClient);

    Page<ClientResponseDto> read(String token, Pageable pageable);

    ClientResponseDto readOne(Long id);

    String delete(Long id);
}
