package com.advance.pharmacie.service.interfaces.sys;

import com.advance.pharmacie.model.sys.ActionSysteme;
import com.advance.pharmacie.model.sys.ModuleSysteme;

import java.util.List;

public interface ModuleSystemeService {

    ModuleSysteme createOrUpdate(ModuleSysteme moduleSysteme);

    List<ModuleSysteme> read();

    ModuleSysteme readOne(Long id);

    String delete(Long id);
}
