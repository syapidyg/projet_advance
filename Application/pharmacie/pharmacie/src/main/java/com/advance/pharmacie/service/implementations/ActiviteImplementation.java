package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoResponse.ActiviteResponseDto;
import com.advance.pharmacie.model.Activite;
import com.advance.pharmacie.repository.ActiviteRepository;
import com.advance.pharmacie.service.interfaces.ActiviteService;
import com.advance.pharmacie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiviteImplementation implements ActiviteService {

    @Autowired
    ActiviteRepository activiteRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public void create(HttpServletRequest request, String details, String parametres, String source) {
//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtUtil.getToken(request);
        Activite activiteToSave = Activite.builder()
                .details(details)
                .parametres(parametres)
                .source(source)
                .dateEvenement(LocalDateTime.now())
                .nomUtilisateur(jwtUtil.extractNomUser(token))
                .build();
        activiteRepository.save(activiteToSave);
    }

    @Override
    public Page<ActiviteResponseDto> readByUsername(String name, Pageable pageable) {
//        Page<Activite> activite = activiteRepository.findByNomUtilisateur(name, pageable);
        return ActiviteResponseDto.entityToDtoList(activiteRepository.findByNomUtilisateurOrderByDateEvenementDesc(name, pageable));
    }

    @Override
    public ActiviteResponseDto lastConnexion(String name){
        List<Activite> activites = activiteRepository.findByDetailsAndNomUtilisateurOrderByDateEvenementDesc("deconnexion", name);
        if (!activites.isEmpty()) {
            Activite activite = activites.stream().findFirst().orElseThrow(() -> new RuntimeException("Aucune activité trouvée"));
            // Convertir l'entité Activite en DTO ActiviteResponseDto
            return ActiviteResponseDto.entityToDto(activite);
        }
        return null; // Retourne null si aucune activité de déconnexion n'a été trouvée pour cet utilisateur
    }



}
