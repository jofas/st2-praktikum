package ms3restspringdemo.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ms3restspringdemo.entities.Gericht;
import ms3restspringdemo.entities.Speise;
import ms3restspringdemo.repositories.GerichtRepository;
import ms3restspringdemo.repositories.SpeiseRepository;

@RestController
@RequestMapping("/gerichte")
public class GerichtRestController {
 
    @Autowired
    private GerichtRepository gerichtRepository;
    
    @Autowired
    private SpeiseRepository speiseRepository;
    
    
    // BC4,BC7: Alle Gerichte ausgeben
    @GetMapping
    public ResponseEntity<?> getAllGerichte(@RequestParam(value="search", required = false) String query) {
    	if(query == null)
    		return ResponseEntity.ok().body(gerichtRepository.findAll());
    	
    	// query specified
    	else {
    		
    		// Nur das Suchen nach Gerichten, mit einem Preis hoeher einem bestimmten Wert wird implementiert.
    		// Da wir fuer die Aufgabe nur die eine Option brauchen.		
    		try {
        		
    			if(!query.substring(0, 6).equalsIgnoreCase("preis>")) 
        			throw new Exception("Der erste Teil des Strings muss 'preis>' sein");
        		
    			String preisStr = query.substring(6);
    			double preis = Double.parseDouble(preisStr);
    			
    			return ResponseEntity.ok().body(gerichtRepository.findByPreisGreaterThan(preis));
    			
    		}
    		// fange alle Exceptions auf die Eintreten koennen und gebe einfach die Exception zurueck
    		catch(Exception e){
    			return ResponseEntity.badRequest().body(e);
    		}		
    	}
    }
    

    // A6: ein einzelnes Gericht ausgeben 
    @GetMapping("/{id}")
	public ResponseEntity<?> getKundeById(@PathVariable("id") int id ) {
		Gericht g = gerichtRepository.findOne(id);
		if ( g == null ) return ResponseEntity.notFound().build(); 
		else return ResponseEntity.ok().body(g);
	}
	

    // A5: Ein Gericht loeschen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGericht(@PathVariable("id") int id) {
		if ( gerichtRepository.exists(id) ) {
			gerichtRepository.delete(id);
		    return ResponseEntity.ok().build();
		} 
		else return ResponseEntity.notFound().build();
    }
    
    
    // A1,BC1: Ein Gericht neu anlegen
    @PostMapping
	ResponseEntity<?> add( @RequestBody Gericht input ) {
    	Gericht g = gerichtRepository.save(input);
    	URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand( g.getId() ).toUri();
    	return ResponseEntity.created( location ).body( g );
    }
    
    
    // A3: Den Preis eines Gerichts aendern
    @PutMapping("/{id}/preis")
	ResponseEntity<?> change( @PathVariable("id") int id, @RequestBody double preis) {
		Gericht g = gerichtRepository.findOne(id);
		if ( g == null ) return ResponseEntity.notFound().build();
		else {
			g.setPreis(preis);
			gerichtRepository.save(g);
			return ResponseEntity.ok().body(g);
		}
    }
    
    
    // BC3,BC6: Speisen einem Gericht hinzufuegen
    @PutMapping("/{gericht_id}/speisen/{speise_id}")
    ResponseEntity<?> addSpeise(@PathVariable("gericht_id") int gericht_id, @PathVariable("speise_id") int speise_id) {
    	Gericht g = gerichtRepository.findOne(gericht_id);
    	if(g == null) return ResponseEntity.notFound().build();
    	Speise s = speiseRepository.findOne(speise_id);
    	if(s == null) return ResponseEntity.notFound().build();
    	g.addSpeise(s);
    	s.addGericht(g);
    	gerichtRepository.save(g);
    	speiseRepository.save(s);
    	
    	return ResponseEntity.ok().body(g);
    }
    
    
    // BC6: Speise (Verbindung) für ein Gericht loeschen
    @DeleteMapping("/{gericht_id}/speisen/{speise_id}")
    ResponseEntity<?> removeSpeise(@PathVariable("gericht_id") int gericht_id, @PathVariable("speise_id") int speise_id) {
    	Gericht g = gerichtRepository.findOne(gericht_id);
    	if(g == null) return ResponseEntity.notFound().build();
    	Speise s = speiseRepository.findOne(speise_id);
    	if(s == null) return ResponseEntity.notFound().build();
    	g.removeSpeise(s);
    	s.removeGericht(g);
    	gerichtRepository.save(g);
    	speiseRepository.save(s);  	
    	return ResponseEntity.ok().body(g);
    }
    

    // Ein Gericht aendern (war nicht gefordert)
    @PutMapping("/{id}")
	ResponseEntity<?> change( @PathVariable("id") int id, @RequestBody Gericht input ) {
		Gericht g = gerichtRepository.findOne(id);
		if ( g == null ) return ResponseEntity.notFound().build();
		else {
			g.setName(input.getName() );
			g.setDetails(input.getDetails());
			g.setPreis(input.getPreis());
			gerichtRepository.save(g);
			return ResponseEntity.ok().body(g);
		}
    }
}
