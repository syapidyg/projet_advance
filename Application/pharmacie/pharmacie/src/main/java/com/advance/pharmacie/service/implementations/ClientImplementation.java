package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.ClientRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;
import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.repository.ClientRepository;
import com.advance.pharmacie.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClientImplementation implements ClientService {

    @Autowired
    public ClientRepository clientRepository;


    @Override
    public ClientResponseDto createOrUpdate(ClientRequestDto dtoClient) {

        if (dtoClient.getId() > 0) {

            Client client = clientRepository.findById(dtoClient.getId()).map(p -> {
                p.setName(dtoClient.getName());
                p.setNumber(dtoClient.getNumber());
                p.setEmail(dtoClient.getEmail());
                return clientRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Aucun Client trouvé"));

            return ClientResponseDto.entityToDto(clientRepository.save(client));
        } else {
            Client client = ClientRequestDto.dtoToEntity(dtoClient);
            return ClientResponseDto.entityToDto(clientRepository.save(client));
        }
    }

    @Override
    public List<ClientResponseDto> read() {
        return ClientResponseDto.entityToDtoList(clientRepository.findAll());
    }

    @Override
    public String delete(Long id) {
        clientRepository.deleteById(id);
        return "Client supprimé avec succès";
    }

}