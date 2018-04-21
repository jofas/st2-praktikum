package ms1jpaspringdemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ms1jpaspringdemo.entities.Gericht;
import ms1jpaspringdemo.entities.Speise;
import ms1jpaspringdemo.entities.Zutat;
import ms1jpaspringdemo.entities.Zutatenangabe;
import ms1jpaspringdemo.factories.GerichtFactory;
import ms1jpaspringdemo.factories.ZubereitungsanleitungFactory;
import ms1jpaspringdemo.repositories.GerichtRepository;
import ms1jpaspringdemo.repositories.SpeiseRepository;
import ms1jpaspringdemo.repositories.ZutatRepository;


@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private GerichtRepository gerichtRepository;
	@Autowired
	private ZutatRepository zutatRepository; 
	@Autowired
	private SpeiseRepository speiseRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Hier wird einmal Kartoffelbrei mit Möhren u
		// und einmal Kartoffelbrei mit Erbsen als Gericht erstellt
		
		// Die Sachen werden zur besseren Nachvollziehbarkeit in Hashmaps gespeichert.
		
		// erst einmal Zutaten erstellen
		Map<String, Zutat> zutaten = new HashMap<String, Zutat>();
		for(String s : new String[]{"Kartoffel","Erbse","Möhre","Salz","Butter","Pfeffer"}) {
			Zutat z = new Zutat(s);
			zutaten.put(s, z);
		}
	
		// schließlich die einzelnen Speisen erstellen
		Map<String, Speise> speisen = new HashMap<String, Speise>();
		
		speisen.put("Kartoffelbrei", new Speise("Kartoffelbrei", 
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Kartoffeln, Salz und Butter vermatschen!", 	// Anleitungstext
			Arrays.asList(new Zutatenangabe[]{          	// Zutatenangaben...
			new Zutatenangabe(zutaten.get("Kartoffel"),6),
			new Zutatenangabe(zutaten.get("Butter"),2),
			new Zutatenangabe(zutaten.get("Salz"),5)
		}))));
		
		speisen.put("Möhrengemüse", new Speise("Möhrengemüse", 
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Möhren und Pfeffer umrühren!", 				// Anleitungstext
			Arrays.asList(new Zutatenangabe[]{          	// Zutatenangaben...
			new Zutatenangabe(zutaten.get("Möhre"),3),
			new Zutatenangabe(zutaten.get("Pfeffer"),1)
		}))));
		
		speisen.put("Erbsengemüse", new Speise("Erbsengemüse",
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Erbsen, Salz und Pfeffer verbrennen lassen!",  // Anleitungstext
			Arrays.asList(new Zutatenangabe[]{          	// Zutatenangaben...
			new Zutatenangabe(zutaten.get("Erbse"),100),
			new Zutatenangabe(zutaten.get("Salz"),2),
			new Zutatenangabe(zutaten.get("Pfeffer"),5)
		}))));
		
		// jetzt können die Gerichte aus den Speisen zusammen gesetzte werden
		Gericht gericht1 = GerichtFactory.createGerichtWithSpeisen(
				"Kartoffelbrei mit Möhren", 
				"Voll das Oma-Essen!", 7.5, 
				Arrays.asList(speisen.get("Kartoffelbrei"), speisen.get("Möhrengemüse")));
		// speicher persistent
		gerichtRepository.save(gericht1); 
		
		Gericht gericht2 = GerichtFactory.createGerichtWithSpeisen(
				"Kartoffelbrei mit Erbsen", 
				"Jede Erbse macht einen Knall!", 7.5, 
				Arrays.asList(speisen.get("Kartoffelbrei"), speisen.get("Erbsengemüse")));
		
		// speicher persistent
		/* Hier gibt es ein Problem! Wenn ich das zweite Gericht speichern möchte, dann kommt ein Fehler.
		 * Fehler: object references an unsaved transient instance
		 */
		//TODO Gucken ob, man den Fehler beheben kann, noch mal alle Annotationen checken, finder-Methode implementieren
		
		// gerichtRepository.save(gericht2);
		
		// Ausgabe von ...
		System.out.println(gericht1);
		
	}
	
	
}

