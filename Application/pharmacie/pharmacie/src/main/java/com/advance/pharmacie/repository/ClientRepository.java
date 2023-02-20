package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
