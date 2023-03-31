package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.LigneCommandeResponseDto;
import com.advance.pharmacie.service.interfaces.lnk.LigneCommandeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/ligneDeCommande")

public class LigneCommandeController {

    @Autowired
    LigneCommandeService ligneCommandeService;

    @ApiOperation("Creation et Mise a jour d'une ligne de commande")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<LigneCommandeResponseDto>> create(@RequestBody LigneCommandeRequestDto dto){
        LigneCommandeResponseDto data = ligneCommandeService.createOrUpdate(dto);
        return ResponseEntity.ok(
                ApiResponse.<LigneCommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(data)
                        .build());
    }

    @ApiOperation("Liste des lignes de commande")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<LigneCommandeResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<LigneCommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.read())
                        .build());
    }

    @ApiOperation("Liste d'une ligne de commande")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<LigneCommandeResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<LigneCommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.readOne(id))
                        .build());
    }

   @ApiOperation("Liste des lignes de commande client")
    @GetMapping("/readClient/{id}")
    public ResponseEntity<ApiResponse<List<LigneCommandeResponseDto>>> readClient(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<List<LigneCommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.readClient(id))
                        .build());
    }

    @ApiOperation("Liste des lignes de commande fournisseur")
    @GetMapping("/readFournisseur/{id}")
    public ResponseEntity<ApiResponse<List<LigneCommandeResponseDto>>> readFournisseur(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<List<LigneCommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.readFournisseur(id))
                        .build());
    }

    @ApiOperation("Liste des lignes de commande stock")
    @GetMapping("/readStock/{id}")
    public ResponseEntity<ApiResponse<List<LigneCommandeResponseDto>>> readStock(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<List<LigneCommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.readStock(id))
                        .build());
    }

    @ApiOperation("Suppression d'une ligne de commande")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(ligneCommandeService.delete(id))
                        .build());
    }
}
