package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.ApiResponse;
import com.advance.pharmacie.dto.dtoAuth.AuthenticationRequestDto;
import com.advance.pharmacie.dto.dtoAuth.AuthenticationResponseDto;
import com.advance.pharmacie.dto.dtoRequest.UtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;
import com.advance.pharmacie.service.Auth.UtilisateurService;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin("*")

public class UtilisateurController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ActiviteService activiteService;


    @Autowired
    protected JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponseDto>> login(@RequestBody AuthenticationRequestDto request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = utilisateurService.loadUserByUsername(request.getUsername());
            UtilisateurResponseDto user = utilisateurService.findByUsername(request.getUsername());
            Map<String, Object> claims =  new HashMap<>();
            claims.put("id", user.getId());
            claims.put("nomUser", user.getUsername());
            final String jwt = JwtUtil.generateToken(userDetails.getUsername(), claims);
            AuthenticationResponseDto data = AuthenticationResponseDto
                    .builder()
                    .token(jwt)
                    .utilisateur(user)
                    .build();
            return ResponseEntity.ok(ApiResponse
                   .<AuthenticationResponseDto>
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

    @PostMapping("/signout")
    private void signOut(String statut, HttpServletRequest request){
        activiteService.create(request, "deconnexion", statut, "utilisateurController |  signOut | chemin : /utilisateur/signOut");

    }
}
