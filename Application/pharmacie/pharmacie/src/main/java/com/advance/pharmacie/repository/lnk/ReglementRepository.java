package com.advance.pharmacie.repository.lnk;

import com.advance.pharmacie.model.lnk.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement, Long> {

    List<Reglement> findByCommandeType(String type);

//    Boolean existsByCode(String code);

}
