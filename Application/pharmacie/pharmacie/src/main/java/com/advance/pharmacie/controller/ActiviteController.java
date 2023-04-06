package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoResponse.ActiviteResponseDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
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

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/activite")

public class ActiviteController {


    @Autowired
    ActiviteService activiteService;


    @ApiOperation("Liste des activites")
    @GetMapping("/read/{name}")
    public ResponseEntity<ApiResponse<Page<ActiviteResponseDto>>> read(@PathVariable(name = "name") String name,
                                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<ActiviteResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(activiteService.readByUsername(name, pageable))
                        .build());
    }

//    @ApiOperation("derniere connexion")
//    @GetMapping("/lastConnexion")
//    public ResponseEntity<ApiResponse<Page<ActiviteResponseDto>>> lastConnexion(@RequestParam(name = "name") String name,
//                                                                       @RequestParam(name = "page", defaultValue = "0") int page,
//                                                                       @RequestParam(name = "size", defaultValue = "10") int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return ResponseEntity.ok(
//                ApiResponse.<Page<ActiviteResponseDto>>builder()
//                        .success(true)
//                        .message("Opereation reussie")
//                        .data(activiteService.lastConnexion(name, pageable))
//                        .build());
//    }

    @ApiOperation("derniere connexion")
    @GetMapping("/lastConnexion/{name}")
    public ResponseEntity<ApiResponse<ActiviteResponseDto>> lastConnexion(@PathVariable String name){
        return ResponseEntity.ok(
                ApiResponse.<ActiviteResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(activiteService.lastConnexion(name))
                        .build());
    }
}
