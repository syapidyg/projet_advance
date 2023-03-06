package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.service.interfaces.CommandeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/commande")

public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @ApiOperation("Creation et Mise a jour d'une commande")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CommandeResponseDto>> create(@RequestBody CommandeRequestDto dto){
        CommandeResponseDto data = commandeService.createOrUpdate(dto);
        return ResponseEntity.ok(
                ApiResponse.<CommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(data)
                        .build());
    }

    @ApiOperation("Liste des commandes")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<CommandeResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.read())
                        .build());
    }

    @ApiOperation("Liste d'une comande")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<CommandeResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<CommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'une commande")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.delete(id))
                        .build());
    }
}
