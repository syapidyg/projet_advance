package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.DepotRequestDto;
import com.advance.pharmacie.dto.dtoResponse.DepotResponseDto;
import com.advance.pharmacie.service.interfaces.DepotService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/depot")

public class DepotController {

    @Autowired
    DepotService depotService;

    @ApiOperation("Creation et Mise a jour d'une depot")
    @PostMapping("/Create")
    public ResponseEntity<ApiResponse<DepotResponseDto>> create(@RequestBody DepotRequestDto dto) {
        return ResponseEntity.ok(
                ApiResponse.<DepotResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(depotService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste des depots")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<DepotResponseDto>>> read() {
        return ResponseEntity.ok(
                ApiResponse.<List<DepotResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(depotService.read())
                        .build());
    }

    @ApiOperation("Suppression d'une depot")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(depotService.delete(id))
                        .build());


    }


}
