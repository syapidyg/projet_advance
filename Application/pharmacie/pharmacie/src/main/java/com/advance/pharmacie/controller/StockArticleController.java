package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.model.lnk.StockArticle;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/stockArticle")

public class StockArticleController {

    @Autowired
    private StockArticleService stockArticleService;

    @Autowired
    private ActiviteService activiteService;


    @ApiOperation("Creation et Mise a jour des produits dans un depot donné")
    @PostMapping("/create")
    public void create(@RequestBody List<StockArticleRequestDto> dto) {

        stockArticleService.createOrUpdate(dto);
    }

//    public ResponseEntity<ApiResponse<StockArticleResponseDto>> create(@RequestBody StockArticleRequestDto dto){
//
//        return ResponseEntity.ok(
//                ApiResponse.<StockArticleResponseDto>builder()
//                        .success(true)
//                        .message("Opereation reussie")
//                        .data(stockArticleService.createOrUpdate(dto))
//                        .build());
//    }

    @ApiOperation("Liste des commandes")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<Page<StockArticleResponseDto>>> read(@RequestParam(name = "token", defaultValue = "") String token,
                                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                                            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<StockArticleResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.read(token, pageable))
                        .build());
    }

    @ApiOperation("Vue dans article specifique dans un depot")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<StockArticleResponseDto>> readOne(@PathVariable Long id, HttpServletRequest request) {
        activiteService.create(request, "Lecture d'un produit en stock", id.toString(), "stockArticleController |  readOne | chemin : /stockArticle/readOne");
        return ResponseEntity.ok(
                ApiResponse.<StockArticleResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'un ligne de stock des aticles")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id, HttpServletRequest request) {
        activiteService.create(request, "suppression d'un produit en stock", id.toString(), "stockArticleController |  delete | chemin : /stockArticle/delete");
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(stockArticleService.delete(id))
                        .build());
    }
}
