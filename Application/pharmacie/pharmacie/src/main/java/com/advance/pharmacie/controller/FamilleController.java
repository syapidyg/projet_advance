package com.advance.pharmacie.controller;


import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.FamilleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.dto.dtoResponse.FamilleResponseDto;
import com.advance.pharmacie.service.interfaces.FamilleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/famille")

public class FamilleController {

    @Autowired
    FamilleService familleService;

    @ApiOperation("Creation et Mise a jour d'une famille de produits")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FamilleResponseDto>> create(@RequestBody FamilleRequestDto dto){
        return ResponseEntity.ok(
                ApiResponse.<FamilleResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(familleService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des familles de produits")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<FamilleResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<FamilleResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(familleService.read())
                        .build());
    }

    @ApiOperation("Liste d'une famille")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<FamilleResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<FamilleResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(familleService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'une famille de produits")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(familleService.delete(id))
                        .build());

    }


}
