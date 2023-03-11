package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.DepotRequestDto;
import com.advance.pharmacie.dto.dtoRequest.EmployeUtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.EmployeResponseDto;
import com.advance.pharmacie.service.interfaces.DepotService;
import com.advance.pharmacie.service.interfaces.EmployeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employe")

public class EmployeController {

    @Autowired
    EmployeService employeService;

    @ApiOperation("creation et Mise a jour d'une employe")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<EmployeResponseDto>> create(@RequestBody EmployeUtilisateurRequestDto dto) {
        return ResponseEntity.ok(
                ApiResponse.<EmployeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(employeService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste d'une caisse")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<EmployeResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<EmployeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(employeService.readOne(id))
                        .build());
    }

    @ApiOperation("Liste des employes")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<EmployeResponseDto>>> read() {
        return ResponseEntity.ok(
                ApiResponse.<List<EmployeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(employeService.read())
                        .build());
    }

    @ApiOperation("Suppression d'une employe")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(employeService.delete(id))
                        .build());


    }


}
