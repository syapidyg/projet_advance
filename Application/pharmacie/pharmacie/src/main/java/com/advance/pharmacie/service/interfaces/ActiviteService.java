package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoResponse.ActiviteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface ActiviteService {

    void create(HttpServletRequest request, String details, String parametres, String source);

//    List<ActiviteResponseDto> read();
//
//    ActiviteResponseDto readByUsername(String name);

    Page<ActiviteResponseDto> readByUsername(String name, Pageable pageable);

//    Page<ActiviteResponseDto> lastConnexion(String name, Pageable pageable);

    ActiviteResponseDto lastConnexion(String name);
}
