package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
