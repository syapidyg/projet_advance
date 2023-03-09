package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FournisseurRequestDto {

    private Long id;
    private String email;
    private String name;
    private Long number;
    private String adress;
    private String city;
//    private List<Commande> commandes = new ArrayList<>();

    public static Fournisseur dtoToEntity(FournisseurRequestDto dto) {
        return Fournisseur.FournisseurBuilder.aFournisseur()
                .id(dto.getId())
                .email(dto.getEmail())
                .adress(dto.getAdress())
                .name(dto.getName())
                .number(dto.getNumber())
                .city(dto.getCity())
//                .commandes(dto.getCommandes())
                .build();
    }

}
