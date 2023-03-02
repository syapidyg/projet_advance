//package com.advance.pharmacie.model.lnk;
//
//
//import com.advance.pharmacie.model.AuditEntity;
//import com.advance.pharmacie.model.auth.Role;
//import com.advance.pharmacie.model.auth.Utilisateur;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "lnk_role_utilisateur")
//public class RoleUtilisateur extends AuditEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_role_utilisateur")
//    private Long id;
//
//    @ManyToOne(targetEntity = Utilisateur.class)
//    @JoinColumn(name = "id_utilisateur")
//    private Utilisateur utilisateur;
//
//    @ManyToOne(targetEntity = Role.class)
//    @JoinColumn(name = "id_role")
//    private Role role;
//
//    @Column(name = "date_alloc")
//    private LocalDateTime dateAlloc = LocalDateTime.now();
//
//    public static RoleUtilisateur buildFromIdUserIdRole(Long idUser, Long idRole) {
//        return RoleUtilisateur.builder()
//                .utilisateur(Utilisateur.UtilisateurBuilder.anUtilisateur().id(idUser).build())
//                .role(Role.builder().id(idRole).build())
//                .build();
//    }
//}