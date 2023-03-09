package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.FournisseurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.FournisseurResponseDto;
import com.advance.pharmacie.service.interfaces.FournisseurService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fournisseur")

public class FournisseurController {

    @Autowired
    FournisseurService fournisseurService;

    @ApiOperation("Creation et Mise a jour d'une fournisseur")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FournisseurResponseDto>> create(@RequestBody FournisseurRequestDto dto) {
        return ResponseEntity.ok(
                ApiResponse.<FournisseurResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(fournisseurService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des fournisseurs")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<FournisseurResponseDto>>> read() {
        return ResponseEntity.ok(
                ApiResponse.<List<FournisseurResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(fournisseurService.read())
                        .build());
    }

    @ApiOperation("Liste des fournisseurs")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<FournisseurResponseDto>> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<FournisseurResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(fournisseurService.readOne(id))
                        .build());
    }


    @ApiOperation("Suppression d'une fournisseur")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(fournisseurService.delete(id))
                        .build());
    }
}
