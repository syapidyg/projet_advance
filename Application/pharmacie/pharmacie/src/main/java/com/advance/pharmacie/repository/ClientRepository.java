package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c from Client c where c.name like :token ORDER BY c.id DESC ")
    Page<Client> findAllClient(String token, Pageable page);
}
