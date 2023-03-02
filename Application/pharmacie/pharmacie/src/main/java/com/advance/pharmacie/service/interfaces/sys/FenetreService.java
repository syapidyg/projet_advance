package com.advance.pharmacie.service.interfaces.sys;

import com.advance.pharmacie.model.sys.Fenetre;

import java.util.List;

public interface FenetreService {

    Fenetre createOrUpdate(Fenetre fenetre);

    List<Fenetre> read();

    Fenetre readOne(Long id);

    String delete(Long id);
}
