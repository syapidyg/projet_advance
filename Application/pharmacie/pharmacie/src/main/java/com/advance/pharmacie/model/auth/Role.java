package com.advance.pharmacie.model.auth;

import com.advance.pharmacie.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@Table(name = "APP_ROLE")

public class Role extends AuditEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

    public static final class RoleBuilder {
        private Long id;
        private String name;

        private RoleBuilder() {
        }

        public static RoleBuilder aRole() {
            return new RoleBuilder();
        }

        public RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            return role;
        }
    }
}
