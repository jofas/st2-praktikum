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


    // BC4,BC7: Alle Gerichte ausgeben.
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
    		// fange alle Exceptions auf die Eintreten koennen und gebe einfach BadRequest zurueck
    		catch(Exception e){
    			return ResponseEntity.badRequest().build();
    		}
    	}
    }


    // A6: ein einzelnes Gericht ausgeben
    @GetMapping("/{id}")
	public ResponseEntity<?> getKundeById(@PathVariable("id") int id ) {
		Gericht g = gerichtRepository.findOne(id);
		// werfe 404 wenn Gericht nicht existiert
    if ( g == null ) return ResponseEntity.notFound().build();
		// gib Gericht zurueck
    else return ResponseEntity.ok().body(g);
	}


    // A5: Ein Gericht loeschen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGericht(@PathVariable("id") int id) {
		// gib leeren 200 zurueck, falls loeschen erfolgreich
    if ( gerichtRepository.exists(id) ) {
      // eigentliches loeschen
			gerichtRepository.delete(id);
		    return ResponseEntity.ok().build();
		}
    // 404
		else return ResponseEntity.notFound().build();
    }


    // A1,BC1: Ein Gericht neu anlegen
    @PostMapping
	ResponseEntity<?> add( @RequestBody Gericht input ) {
    	// lege neues Gericht mit Repository der Gerichtsentitaet an
      Gericht g = gerichtRepository.save(input);
    	// location des neuen Gerichts
      URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand( g.getId() ).toUri();
    	// 201 mit der location im Header und als Body das
      // neue Gericht
      return ResponseEntity.created( location ).body( g );
    }


    // A3: Den Preis eines Gerichts aendern
    @PutMapping("/{id}/preis")
	ResponseEntity<?> change( @PathVariable("id") int id, @RequestBody double preis) {
		Gericht g = gerichtRepository.findOne(id);
		// 404
    if ( g == null ) return ResponseEntity.notFound().build();
		else {
      // neuen Preis speichern
			g.setPreis(preis);
			gerichtRepository.save(g);
      // 200 mit dem neuen Gericht im Body
			return ResponseEntity.ok().body(g);
		}
    }


    // BC3,BC6: Speisen einem Gericht hinzufuegen
    @PutMapping("/{gericht_id}/speisen/{speise_id}")
    ResponseEntity<?> addSpeise(@PathVariable("gericht_id") int gericht_id, @PathVariable("speise_id") int speise_id) {
    	// als erstes wird das Gericht mit gericht_id aus der Datenbank geholt
      Gericht g = gerichtRepository.findOne(gericht_id);
      // 404 wenn das Gericht nicht existiert
    	if(g == null) return ResponseEntity.notFound().build();
    	// dann die Speise mit speise_id
      Speise s = speiseRepository.findOne(speise_id);
    	// auch hier 404 falls Speise nicht vorhanden
      if(s == null) return ResponseEntity.notFound().build();
    	// Referenzen fuer beide setzen und beide speichern
      g.addSpeise(s);
    	s.addGericht(g);
    	gerichtRepository.save(g);
    	speiseRepository.save(s);
      // 200 mit dem upgedatetem Gericht
    	return ResponseEntity.ok().body(g);
    }

    // BC6: Speise (Verbindung) fuer ein Gericht loeschen
    @DeleteMapping("/{gericht_id}/speisen/{speise_id}")
    ResponseEntity<?> removeSpeise(@PathVariable("gericht_id") int gericht_id, @PathVariable("speise_id") int speise_id) {
    	// vgl. addSpeise
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
