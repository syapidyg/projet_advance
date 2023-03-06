package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import com.advance.pharmacie.service.interfaces.ProduitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/produit")

public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @ApiOperation("Creation et Mise a jour d'un produit")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProduitResponseDto>> create(@RequestBody ProduitRequestDto dto){

        return ResponseEntity.ok(
                ApiResponse.<ProduitResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste de tous les produits")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<ProduitResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<ProduitResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.read())
                        .build());
    }

    @ApiOperation("Listing d'un produit")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<ProduitResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<ProduitResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'un produit")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.delete(id))
                        .build());
    }
}