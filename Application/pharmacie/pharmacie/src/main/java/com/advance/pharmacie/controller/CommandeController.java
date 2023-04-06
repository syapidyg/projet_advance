package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoRequest.BonToFactureRequestDto;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.service.interfaces.CommandeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/commande")

public class CommandeController {

    @Autowired
    CommandeService commandeService;
    @Autowired
    ActiviteService activiteService;

    @ApiOperation("Creation et Mise a jour d'une commande")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CommandeResponseDto>> create(@RequestBody CommandeRequestDto dto, HttpServletRequest request) {
        activiteService.create(request, "Creation d'une commande", dto.toString(), "commandeController |  create | chemin : /commande/create");
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
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> read(@RequestParam(name = "token", defaultValue = "") String token,
                                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                                       @RequestParam(name = "type", defaultValue = "") String type,
                                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.read(type, token, pageable))
                        .build());
    }

    @ApiOperation("Liste des commandes clients")
    @GetMapping("/readClient")
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> readClient(@RequestParam(name = "token", defaultValue = "") String token,
                                                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                                                             @RequestParam(name = "document", defaultValue = "") String document,
                                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readClient("client", document, token, pageable))
                        .build());
    }

    @ApiOperation("Liste des commandes stock")
    @GetMapping("/readStock")
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> readStock(@RequestParam(name = "token", defaultValue = "") String token,
                                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                                                            HttpServletRequest request) {

        activiteService.create(request, "Consultez les mouvements de stock", "token: " +token, "commandeController |  readStock | chemin : /commande/readStock");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readStock("stock", token, pageable))
                        .build());
    }

    @ApiOperation("Liste des commandes fournisseur")
    @GetMapping("/readFournisseur")
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> readFournisseur(@RequestParam(name = "token", defaultValue = "") String token,
                                                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                                                                  HttpServletRequest request) {
        activiteService.create(request, "Lecture des commandes des fournisseurs",  "token: " + token + page + size, "commandeController |  read | chemin : /commande/readFournisseurs");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readFournisseur("fournisseur", token, pageable))
                        .build());
    }

    @ApiOperation("Liste des ligne de commande sur le type")
    @GetMapping("/readType")
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> readType(@RequestParam(name = "token", defaultValue = "") String token,
                                                                           @RequestParam(name = "type", defaultValue = "client") String type,
                                                                           @RequestParam(name = "document", defaultValue = "") String document,
                                                                           @RequestParam(name = "dateDebut", defaultValue = "01-01-2015") String dateDebut,
                                                                           @RequestParam(name = "dateFin", defaultValue = "") String dateFin,
                                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                                           @RequestParam(name = "size", defaultValue = "10") int size) throws ParseException {
        if (dateFin.length() <= 0) {
            dateFin = String.valueOf(LocalDate.now());
        }
        Pageable pageable = PageRequest.of(page, size);
        int sizeOf = size;
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readByType(type, document, dateDebut, dateFin, token, pageable, sizeOf))
                        .build());
    }

    @ApiOperation("Liste des ligne de commande sur le stock")
    @GetMapping("/readStoc")
    public ResponseEntity<ApiResponse<Page<CommandeResponseDto>>> readStock(@RequestParam(name = "token", defaultValue = "") String token,
                                                                            @RequestParam(name = "type", defaultValue = "client") String type,
                                                                            @RequestParam(name = "document", defaultValue = "") String document,
                                                                            @RequestParam(name = "dateDebut", defaultValue = "01-01-2015") String dateDebut,
                                                                            @RequestParam(name = "dateFin", defaultValue = "") String dateFin,
                                                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                                                            @RequestParam(name = "size", defaultValue = "10") int size) throws ParseException {
        if (dateFin.length() <= 0) {
            dateFin = String.valueOf(LocalDate.now());
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                ApiResponse.<Page<CommandeResponseDto>>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readByType(type, document, dateDebut, dateFin, token, pageable, size))
                        .build());
    }

    @ApiOperation("Liste d'une comande")
    @GetMapping("/readOne/{id}")
    public ResponseEntity<ApiResponse<CommandeResponseDto>> readOne(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.<CommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.readOne(id))
                        .build());
    }

    @ApiOperation("Suppression d'une commande")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id, HttpServletRequest request) {
        activiteService.create(request, "Supression d'une commande", id.toString(), "commandeController |  delete | chemin : /commande/delete");
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(commandeService.delete(id))
                        .build());
    }

    @ApiOperation("Transformation du bon de commande en facture")
    @PostMapping("/transform")
    public ResponseEntity<ApiResponse<CommandeResponseDto>> tBonFacture(@RequestBody BonToFactureRequestDto dto, HttpServletRequest request) {
        activiteService.create(request, "Reglement de la facture", dto.toString(), "commandeController |  read | chemin : /commande/transform");
        CommandeResponseDto data = commandeService.tBonFacture(dto);
        return ResponseEntity.ok(
                ApiResponse.<CommandeResponseDto>builder()
                        .success(true)
                        .message("Opereation reussie")
                        .data(data)
                        .build());
    }
}
