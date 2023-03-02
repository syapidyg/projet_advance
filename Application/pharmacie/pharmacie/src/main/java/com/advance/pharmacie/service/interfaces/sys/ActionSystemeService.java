package com.advance.pharmacie.service.interfaces.sys;

import com.advance.pharmacie.model.sys.ActionSysteme;

import java.util.List;

public interface ActionSystemeService {

    ActionSysteme createOrUpdate(ActionSysteme actionSysteme);

    List<ActionSysteme> read();

    ActionSysteme readOne(Long id);

    String delete(Long id);
}
