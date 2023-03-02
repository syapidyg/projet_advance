package com.advance.pharmacie.repository.auth;

import com.advance.pharmacie.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
