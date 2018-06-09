package com.example.services;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entities.Kunde;
import com.example.factories.KundeFactory;
import com.example.repositories.KundeRepository;

@RestController
@RequestMapping("/kunden")
public class KundeRestController {
 
    @Autowired
    private KundeRepository kundeRepository;
    
    /**
     * Alle Kunden auslesen
     * @return
     */
    @GetMapping
    public List<Kunde> getAllKunden() {
		return (List<Kunde>) kundeRepository.findAll();
    }
	
    /**
     * Einen bestimmten Kunden auslesen
     * @return
     */
    @GetMapping("/{id}")
	public ResponseEntity<?> getKundeById(@PathVariable("id") Long id ) {
		Kunde k = kundeRepository.findOne(id);
		if ( k == null ) return ResponseEntity.notFound().build();
		else return ResponseEntity.ok().body( k );
	}

    /**
     * Einen Kunden löschen
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKunde(@PathVariable("id") Long id) {
		if ( kundeRepository.exists(id) ) {
			kundeRepository.delete(id);
		    return ResponseEntity.ok().build();
		} 
		else return ResponseEntity.notFound().build();
    }
    
    
    /**
     * Einen Kunden neu anlegen
     * @return
     */
    @PostMapping
	ResponseEntity<?> add( @RequestBody Kunde input ) {
    	Kunde k = kundeRepository.save(
    		new KundeFactory().createKunde( input.getName(), input.getVorname() ) );
    	URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand( k.getId() ).toUri();
    	return ResponseEntity.created( location ).body( k );
    }
    
    /**
     * Einen Kunden ändern
     * @return
     */
    @PutMapping("/{id}")
	ResponseEntity<?> change( @PathVariable("id") Long id, @RequestBody Kunde input ) {
		Kunde k = kundeRepository.findOne(id);
		if ( k == null ) return ResponseEntity.notFound().build();
		else {
			k.setName( input.getName() );
			k.setVorname( input.getVorname() );
			kundeRepository.save( k );
			return ResponseEntity.ok().body( k );
		}
    }
    
}
