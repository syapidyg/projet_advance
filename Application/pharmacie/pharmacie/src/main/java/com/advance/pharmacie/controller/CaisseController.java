package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.CaisseRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CaisseResponseDto;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.service.interfaces.CaisseService;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/caisse")

public class CaisseController {

    @Autowired
    CaisseService caisseService;
    @Autowired
    ActiviteService activiteService;


    @ApiOperation("Creation et Mise a jour d'une caisse")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CaisseResponseDto>> create(@RequestBody CaisseRequestDto dto, HttpServletRequest request){
        activiteService.create(request, "Creation d'une caisse", dto.toString(), "CaisseController |  create | chemin : /caisse/create");
       CaisseResponseDto data = caisseService.createOrUpdate(dto);
        return ResponseEntity.ok(
                ApiResponse.<CaisseResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(data)
                        .build());
    }

    @ApiOperation("Liste des caisses")
    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<CaisseResponseDto>>> read(){
        return ResponseEntity.ok(
                ApiResponse.<List<CaisseResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.read())
                        .build());
    }

    @ApiOperation("Liste d'une caisse")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<CaisseResponseDto>> readOne(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<CaisseResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'une caisse")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id, HttpServletRequest request){
        activiteService.create(request, "Suppression d'une caisse", id.toString(), "CaisseController |  delete | chemin : /caisse/delete");
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(caisseService.delete(id))
                        .build());
    }

}
