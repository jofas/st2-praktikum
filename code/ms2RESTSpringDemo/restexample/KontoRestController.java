package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Konto;
import com.example.entities.Kunde;
import com.example.repositories.KontoRepository;

@RestController
@RequestMapping("/konten")
public class KontoRestController {
 
    @Autowired
    private KontoRepository kontoRepository;
    
    /**
     * Alle Konten auslesen
     * @return
     */
    @GetMapping
    public List<Konto> getAllKonten() {
		return (List<Konto>) kontoRepository.findAll();
    }
	
    // weitere Methoden hier ausgelassen - siehe KundeRestController für mehr Details
}
