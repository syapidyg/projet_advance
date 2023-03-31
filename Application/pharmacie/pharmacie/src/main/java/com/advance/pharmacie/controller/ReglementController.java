package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.ReglementRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.ReglementResponseDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.service.interfaces.lnk.ReglementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/reglement")
public class ReglementController {

    @Autowired
    ReglementService reglementService;

    @ApiOperation("Creation et Mise a jour d'un reglement")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReglementResponseDto>> create(@RequestBody ReglementRequestDto dto){
        ReglementResponseDto data = reglementService.createOrUpdate(dto);
        return ResponseEntity.ok(
                ApiResponse.<ReglementResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(data)
                        .build());
    }

    @ApiOperation("Liste des reglements")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<Page<ReglementResponseDto>>> read(@RequestParam(name = "token", defaultValue = "") String token,
                                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<ReglementResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(reglementService.read(token, pageable))
                        .build());
    }
    @ApiOperation("Liste d'une reglement")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<ReglementResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<ReglementResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(reglementService.readOne(id))
                        .build());
    }

    @ApiOperation("Liste des reglements fournisseur")
    @GetMapping("/read/readFournisseur")
    public ResponseEntity<ApiResponse<Page<ReglementResponseDto>>> readFournisseur(@RequestParam(name = "token", defaultValue = "") String token,
                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<ReglementResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(reglementService.readFournisseur(token, pageable))
                        .build());
    }

 @ApiOperation("Liste des reglements client")
    @GetMapping("/read/readClient")
 public ResponseEntity<ApiResponse<Page<ReglementResponseDto>>> readClient(@RequestParam(name = "token", defaultValue = "") String token,
                                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                @RequestParam(name = "size", defaultValue = "10") int size) {
     Pageable pageable = PageRequest.of(page, size);
     return ResponseEntity.ok(
             ApiResponse.<Page<ReglementResponseDto>>builder()
                     .success(true)
                     .message("Opereation reussie")
                     .data(reglementService.readClient(token, pageable))
                     .build());
 }


    @ApiOperation("Suppression d'un reglement")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(reglementService.delete(id))
                        .build());
    }
}
