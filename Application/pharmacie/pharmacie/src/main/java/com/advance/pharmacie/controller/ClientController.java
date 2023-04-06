package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.ClientRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.service.interfaces.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/client")

public class ClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ActiviteService activiteService;

    @ApiOperation("Creation et Mise a jour d'une client")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ClientResponseDto>> create(@RequestBody ClientRequestDto dto, HttpServletRequest request) {
        activiteService.create(request, "Creation d'un patient", dto.toString(), "ClientController |  create | chemin : /client/create");
        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des clients")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<Page<ClientResponseDto>>> read(@RequestParam(name = "token", defaultValue = "") String token,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<ClientResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.read(token, pageable))
                        .build());
    }

    @ApiOperation("Afficher un client")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<ClientResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'un client")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id, HttpServletRequest request) {
        activiteService.create(request, "Suppression d'un patient", id.toString(), "ClientController |  delete | chemin : /client/delete");
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(clientService.delete(id))
                        .build());
    }
}
