package ms3restspringdemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ms3restspringdemo.entities.Gericht;
import ms3restspringdemo.entities.Speise;
import ms3restspringdemo.entities.Zutat;
import ms3restspringdemo.entities.Zutatenmenge;
import ms3restspringdemo.factories.GerichtFactory;
import ms3restspringdemo.factories.ZubereitungsanleitungFactory;
import ms3restspringdemo.repositories.GerichtRepository;
import ms3restspringdemo.repositories.SpeiseRepository;
import ms3restspringdemo.repositories.ZutatRepository;


@Component
public class SampleData implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private GerichtRepository gerichtRepository;
	@Autowired
	private ZutatRepository zutatRepository; 
	@Autowired
	private SpeiseRepository speiseRepository;

	// DATEN EINFUEGEN FUER DEN 3 MEILENSTEIN AUSKOMMENTIERT!!!
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		/* Hier wird einmal Kartoffelbrei mit Möhren 
		 und einmal Kartoffelbrei mit Erbsen als Gericht erstellt*/
		
		// Die Sachen werden zur besseren Nachvollziehbarkeit in Hashmaps gespeichert.
		
		
		/*
		// erst einmal Zutaten erstellen
		Map<String, Zutat> zutaten = new HashMap<String, Zutat>();
		for(String s : new String[]{"Kartoffel","Erbse","Möhre","Salz","Butter","Pfeffer"}) {
			Zutat z = new Zutat(s);
			zutaten.put(s, z);
		}
		// Zutaten persistieren
		zutatRepository.save(zutaten.values());
	
		// schließlich die einzelnen Speisen erstellen
		Map<String, Speise> speisen = new HashMap<String, Speise>();	
		speisen.put("Kartoffelbrei", new Speise("Kartoffelbrei", 
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Kartoffeln, Salz und Butter vermatschen!", 	// Anleitungstext
			Arrays.asList(new Zutatenmenge[]{          	// Zutatenangaben...
			new Zutatenmenge(zutaten.get("Kartoffel"),6),
			new Zutatenmenge(zutaten.get("Butter"),2),
			new Zutatenmenge(zutaten.get("Salz"),5)
		}))));
		
		speisen.put("Möhrengemüse", new Speise("Möhrengemüse", 
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Möhren und Pfeffer umrühren!", 				// Anleitungstext
			Arrays.asList(new Zutatenmenge[]{          	// Zutatenangaben...
			new Zutatenmenge(zutaten.get("Möhre"),3),
			new Zutatenmenge(zutaten.get("Pfeffer"),1)
		}))));
		
		speisen.put("Erbsengemüse", new Speise("Erbsengemüse",
			ZubereitungsanleitungFactory.createZubereitungsanleitungWithAngaben(
			"Erbsen, Salz und Pfeffer verbrennen lassen!",  // Anleitungstext
			Arrays.asList(new Zutatenmenge[]{          	// Zutatenangaben...
			new Zutatenmenge(zutaten.get("Erbse"),100),
			new Zutatenmenge(zutaten.get("Salz"),2),
			new Zutatenmenge(zutaten.get("Pfeffer"),5)
		}))));
		
		//  Die Speisen persistent in der Datenbank speichern
		speiseRepository.save(speisen.values());
		
		// jetzt können die Gerichte aus den Speisen zusammen gesetzt werden
		Gericht gericht1 = GerichtFactory.createGerichtWithSpeisen(
				"Kartoffelbrei mit Möhren", 
				"Voll das Oma-Essen!", 7.5, 
				Arrays.asList(speisen.get("Kartoffelbrei"), speisen.get("Möhrengemüse")));
		// speicher persiste
		gerichtRepository.save(gericht1); 
		
		Gericht gericht2 = GerichtFactory.createGerichtWithSpeisen(
				"Kartoffelbrei mit Erbsen", 
				"Jede Erbse macht einen Knall!", 8.5, 
				Arrays.asList(speisen.get("Kartoffelbrei"), speisen.get("Erbsengemüse")));
		
		
		
		gerichtRepository.save(gericht2);
		
		// Ausgabe von Gericht 1
		System.out.println(gericht1);
		// Zeile zur Formatierung
		System.out.println();
		// Ausgabe von Gericht 2
		System.out.println(gericht2);
		
		// gib alle Speisen aus, die Salz enthalten
		System.out.println("\nSalzige Speisen: ");
		speiseRepository.findByContainsZutat(zutaten.get("Salz")).forEach(s -> System.out.println(s.getName()));
		
		// gib alle Zutaten aus, die ein 'f' im Namen haben
		System.out.println("\nAlle Zutaten mit einem 'f' im Namen: ");
		zutatRepository.findByNameLike("%f%").forEach(System.out::println);
		
		// gib alle Gericht unter 8 € aus
		System.out.println("\nAlle Gerichte unter 7 €: ");
		gerichtRepository.findByPreisLessThan(8.0).forEach(g -> System.out.println(g.getName()));
		
		*/
	}
	
	// Aufgabe 2 fertig!
}

