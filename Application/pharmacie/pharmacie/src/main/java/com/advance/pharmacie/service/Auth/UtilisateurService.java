package com.advance.pharmacie.service.Auth;

import com.advance.pharmacie.dto.dtoRequest.UtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;
import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.repository.auth.UtilisateurRepository;
import com.advance.pharmacie.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class UtilisateurService implements UserDetailsService {


    @Autowired
    private UtilisateurRepository utilsateurRepository;

    GeneralUtil generalUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilsateurRepository
                .findByUsername(username)
                .orElseThrow(()->new RuntimeException("Could not find"));

        return new User(utilisateur.getUsername(), utilisateur.getPassword(), Collections.emptyList());
    }


    public UtilisateurResponseDto findByUsername(String username){
        Utilisateur utilisateur =  utilsateurRepository.findByUsername(username).orElse(null);

        return UtilisateurResponseDto.entityToDto(utilisateur);
                }

    public boolean existsByEmail(String username){
        return utilsateurRepository.existsByUsername(username);
    }

    public UtilisateurResponseDto register(UtilisateurRequestDto signinDto){

        if (utilsateurRepository.existsByUsername(signinDto.getUsername())){
            throw new RuntimeException("Cet Username existe deja");
        }

        Utilisateur utilisateur = utilsateurRepository.save(UtilisateurRequestDto.dtoToEntity(signinDto));
        return UtilisateurResponseDto.entityToDto(utilisateur);
    }

    public void lastConnexion(String statut){

    }

}