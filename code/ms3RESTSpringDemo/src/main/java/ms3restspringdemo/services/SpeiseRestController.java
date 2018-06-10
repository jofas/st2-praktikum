package ms3restspringdemo.services;

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

import ms3restspringdemo.entities.Speise;
import ms3restspringdemo.repositories.SpeiseRepository;

@RestController
@RequestMapping("/speisen")
public class SpeiseRestController {
 
    @Autowired
    private SpeiseRepository speiseRepository;
    
    // BC5: Alle Speisen auslesen
    @GetMapping
    public List<Speise> getAllSpeisen() {
		return (List<Speise>) speiseRepository.findAll();
    }
	
    
    // BC2: Einen Speise neu anlegen
    @PostMapping
	ResponseEntity<?> add( @RequestBody Speise input ) {
    	// Die Zubereitungsanleitung erst mal leer lassen
    	Speise s = new Speise(input.getName(), null);
    	speiseRepository.save(s);
    	URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand( s.getId() ).toUri();
    	return ResponseEntity.created( location ).body( s );
    }
    
    
    // Eine Speisen loeschen (war nicht gefordert)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpeise(@PathVariable("id") int id) {
		if ( speiseRepository.exists(id) ) {
			speiseRepository.delete(id);
		    return ResponseEntity.ok().build();
		} 
		else return ResponseEntity.notFound().build();
    }
    
    
    // Einen bestimmten Speise auslesen (war nicht gefordert)
    @GetMapping("/{id}")
	public ResponseEntity<?> getSpeiseById(@PathVariable("id") int id ) {
		Speise s = speiseRepository.findOne(id);
		if ( s == null ) return ResponseEntity.notFound().build();
		else return ResponseEntity.ok().body( s );
	}
    

    // Eine Speise aendern (war nicht gefordert)
    @PutMapping("/{id}")
	ResponseEntity<?> change( @PathVariable("id") int id, @RequestBody Speise input ) {
		Speise s = speiseRepository.findOne(id);
		if ( s == null ) return ResponseEntity.notFound().build();
		else {
			s.setName(input.getName());
			s.setAnleitung(input.getZubereitungsanleitung());
			speiseRepository.save(s);
			return ResponseEntity.ok().body(s);
		}
    }
    
}
