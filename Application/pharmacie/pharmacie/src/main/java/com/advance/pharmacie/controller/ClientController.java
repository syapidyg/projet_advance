package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.ClientRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;
import com.advance.pharmacie.service.interfaces.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/client")

public class ClientController {

    @Autowired
    ClientService clientService;

    @ApiOperation("Creation et Mise a jour d'une client")
    @PostMapping("/Create")
    public ResponseEntity<ApiResponse<ClientResponseDto>> create(@RequestBody ClientRequestDto dto){
        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des clients")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<ClientResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.read())
                        .build());
    }

    @ApiOperation("Suppression d'un client")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.delete(id))
                        .build());
    }
}
