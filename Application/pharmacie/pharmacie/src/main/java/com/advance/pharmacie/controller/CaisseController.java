package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.service.interfaces.CaisseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/caisse")

public class CaisseController {

    @Autowired
    CaisseService caisseService;


    @ApiOperation("Creation et Mise a jour d'une caisse")
    @PostMapping("/Create")
    public ResponseEntity<ApiResponse<CaisseResponseDto>> create(@RequestBody CaisseRequestDto dto){
        return ResponseEntity.ok(
                ApiResponse.<CaisseResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Listing des caisses")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<CaisseResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<CaisseResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.read())
                        .build());
    }

    @ApiOperation("Suppression d'une caisse")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.delete(id))
                        .build());
    }

}
