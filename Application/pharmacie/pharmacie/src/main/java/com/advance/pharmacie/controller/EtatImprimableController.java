package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.dtoRequest.EtatImprimableDto;
import com.advance.pharmacie.service.implementations.SimpleReportImplementation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.sql.SQLException;



public class EtatImprimableController {

    @Autowired
    SimpleReportImplementation simpleReportImplementation;

    ResponseEntity<byte[]> ImprimerEtat(@RequestBody EtatImprimableDto etatImprimableDto) throws SQLException, IOException, JRException {

        return simpleReportImplementation.imprimerEtat(etatImprimableDto);
    }
}
