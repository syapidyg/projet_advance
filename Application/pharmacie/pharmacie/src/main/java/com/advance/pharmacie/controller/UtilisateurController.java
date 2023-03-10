package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoAuth.AuthenticationRequestDto;
import com.advance.pharmacie.dto.dtoAuth.AuthenticationResponseDto;
import com.advance.pharmacie.dto.dtoRequest.UtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;

import com.advance.pharmacie.service.Auth.UtilisateurService;
import com.advance.pharmacie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin("*")

public class UtilisateurController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UtilisateurService utilisateurService;


    @Autowired
    protected JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponseDto>> login(@RequestBody AuthenticationRequestDto request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            final UserDetails userDetails = utilisateurService.loadUserByUsername(request.getUsername());
            final String jwt = JwtUtil.generateToken(userDetails.getUsername());

            utilisateurService.findByUsername(request.getUsername());
            AuthenticationResponseDto data = AuthenticationResponseDto
                    .builder()
                    .token(jwt)
                    .utilisateur(utilisateurService.findByUsername(request.getUsername()))
                    .build();
            return ResponseEntity.ok(ApiResponse
                   .< AuthenticationResponseDto>
                            builder()
                            .success(true)
                            .message("Authentification reuissie")
                            .data(data)
                            .build());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


    @PostMapping("/register")

    private ResponseEntity<ApiResponse<UtilisateurResponseDto>> register(@RequestBody UtilisateurRequestDto user){

        return ResponseEntity.ok(
                ApiResponse.<UtilisateurResponseDto>builder()
                        .message("Enregistrement reussie")
                        .success(true)
                        .data(utilisateurService.register(user))
                        .build()
        );
    }
}
