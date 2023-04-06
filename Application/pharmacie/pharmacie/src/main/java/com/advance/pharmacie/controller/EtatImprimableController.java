package com.advance.pharmacie.controller;

import com.advance.pharmacie.dto.dtoRequest.EtatImprimableDto;
import com.advance.pharmacie.service.implementations.SimpleReportImplementation;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@CrossOrigin("*")
@RequestMapping("/etat")


public class EtatImprimableController {

    @Autowired
    SimpleReportImplementation simpleReportImplementation;

    @ApiOperation("Impression d'un état")
    @PostMapping("/imprimer")
    ResponseEntity<byte[]> ImprimerEtat(@RequestBody EtatImprimableDto etatImprimableDto) throws SQLException, IOException, JRException {

        return simpleReportImplementation.imprimerEtat(etatImprimableDto);
    }
}
