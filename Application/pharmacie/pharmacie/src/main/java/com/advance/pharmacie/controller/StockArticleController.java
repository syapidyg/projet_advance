package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.service.interfaces.ProduitService;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/StockArticle")

public class StockArticleController {

    @Autowired
    private StockArticleService stockArticleService;

    @ApiOperation("Creation et Mise a jour des produits dans un depot donné")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<StockArticleResponseDto>> create(@RequestBody StockArticleRequestDto dto){

        return ResponseEntity.ok(
                ApiResponse.<StockArticleResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.createOrUpdate(dto))
                        .build());
    }

    @ApiOperation("Liste de tous les articles dans un stock")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<StockArticleResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<StockArticleResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.read())
                        .build());
    }

    @ApiOperation("Vue dans article specifique dans un depot")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<StockArticleResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<StockArticleResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'un ligne de stock des aticles")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.delete(id))
                        .build());
    }
}
