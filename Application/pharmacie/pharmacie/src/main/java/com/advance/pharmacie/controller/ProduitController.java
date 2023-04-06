package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.service.interfaces.ProduitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin("*")
@RestController
@RequestMapping("/produit")

public class ProduitController {

    @Autowired
    private ProduitService produitService;

   @Autowired
    private ActiviteService activiteService;

    @ApiOperation("Creation et Mise a jour d'un produit")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProduitResponseDto>> create(@RequestBody ProduitRequestDto dto, HttpServletRequest request){
        activiteService.create(request, "Creation d'un produit", dto.toString(), "produitController |  create | chemin : /produit/create");
        return ResponseEntity.ok(
                ApiResponse.<ProduitResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des produits")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<Page<ProduitResponseDto>>> read(@RequestParam(name = "token", defaultValue = "") String token,
                                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<ProduitResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.read(token, pageable))
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
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id, HttpServletRequest request){
        activiteService.create(request, "suppression d'un produit", id.toString(), "produitController |  delete | chemin : /produit/delete");
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(produitService.delete(id))
                        .build());
    }
}