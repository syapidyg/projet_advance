package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.ClientRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;

import java.util.List;

public interface ClientService {

    ClientResponseDto createOrUpdate(ClientRequestDto dtoClient);

    List<ClientResponseDto> read();

    String delete(Long id);
}
