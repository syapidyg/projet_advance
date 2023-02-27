package com.advance.pharmacie.service.Auth;

import com.advance.pharmacie.dto.dtoRequest.UtilisateurRequestDto;
import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;
import com.advance.pharmacie.model.Utilisateur;
import com.advance.pharmacie.repository.UtilisateurRepository;
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
                .findByNom(username)
                .orElseThrow(()->new RuntimeException("Could not find"));

        return new User(utilisateur.getNom(), utilisateur.getPassword(), Collections.emptyList());
    }

    UtilisateurResponseDto findByName(String username){
        Utilisateur utilisateur =  utilsateurRepository.findByNom(username).orElse(null);

        return UtilisateurResponseDto.builder().resultat((utilisateur == null)? null:utilisateur.getEmail()).build();
    }

    public boolean existsByEmail(String email){
        return utilsateurRepository.existsByEmail(email);
    }

    public UtilisateurResponseDto register(UtilisateurRequestDto signinDto){

        if (utilsateurRepository.existsByEmail(signinDto.getEmail())){
            throw new RuntimeException("Cette adresse mail existe deja");
        }

        Utilisateur reponse = utilsateurRepository.save(UtilisateurRequestDto.dtoToEntity(signinDto));
        return UtilisateurResponseDto.builder().resultat(reponse.getEmail()).build();
    }
}