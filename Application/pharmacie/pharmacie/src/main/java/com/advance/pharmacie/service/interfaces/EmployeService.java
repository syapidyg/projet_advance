package com.advance.pharmacie.service.interfaces;

import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoRequest.EmployeRequestDto;
import com.advance.pharmacie.dto.dtoRequest.EmployeUtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.EmployeResponseDto;

import java.util.List;

public interface EmployeService {

    EmployeResponseDto createOrUpdate(EmployeUtilisateurRequestDto dtoEmploye);

    List<EmployeResponseDto> read();

    EmployeResponseDto readOne(Long id);

    String delete(Long id);
}
