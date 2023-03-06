package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.repository.ClientRepository;
import com.advance.pharmacie.repository.CommandeRepository;
import com.advance.pharmacie.repository.FournisseurRepository;
import com.advance.pharmacie.service.interfaces.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommandeImplementation implements CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCommande) {

        Client client = null;
        Fournisseur fournisseur =null;
        if (dtoCommande.getType().equals("client")) {
             client = clientRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new RuntimeException("Client non existant"));
        }else if (dtoCommande.getType().equals("fournisseur")){
             fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));
        }else {
            new RuntimeException("Choississez un type a inserer et une personne valide");
        }

        if (Objects.nonNull(dtoCommande.getId()) && dtoCommande.getId() > 0) {

            Fournisseur finalFournisseur = fournisseur;
            Client finalClient = client;
            Commande commande = commandeRepository.findById(dtoCommande.getId()).map(p -> {
                p.setStatut(dtoCommande.getStatut());
                p.setType(dtoCommande.getType());
                if(finalClient != null){
                    p.setClient(finalClient);
                }else if(finalFournisseur != null){
                    p.setFournisseur(finalFournisseur);
                }
                return commandeRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("Commande introuvable")));

            return CommandeResponseDto.entityToDto(commandeRepository.save(commande));
        }

        Commande commande = CommandeRequestDto.dtoToEntity(dtoCommande, client, fournisseur);
        return CommandeResponseDto.entityToDto(commandeRepository.save(commande));

    }

    @Override
    public List<CommandeResponseDto> read() {

        List<Commande> commandes = commandeRepository.findAll();
        return CommandeResponseDto.entityToDtoList(commandes);
    }

    @Override
    public String delete(Long id) {

        commandeRepository.deleteById(id);
        return "Commande suprimé avec succès";
    }

    @Override
    public CommandeResponseDto readOne(Long id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun Commande ne correspond a cet ID"));
        ;
        return CommandeResponseDto.entityToDto(commande);
    }
}
