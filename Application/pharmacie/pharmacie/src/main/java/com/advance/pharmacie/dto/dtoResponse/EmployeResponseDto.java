package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Employe;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class EmployeResponseDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;

    public static EmployeResponseDto entityToDto(Employe employe) {
        return EmployeResponseDto.builder()
                .id(employe.getId())
                .nom(employe.getNom())
                .prenom(employe.getPrenom())
                .birthday(employe.getBirthday())
                .email(employe.getEmail())
                .number(employe.getNumber())
                .build();
    }

    public static List<EmployeResponseDto> entityToDtoList(List<Employe> employeList){
        return employeList.stream().map(EmployeResponseDto::entityToDto).collect(Collectors.toList());
    }
}
