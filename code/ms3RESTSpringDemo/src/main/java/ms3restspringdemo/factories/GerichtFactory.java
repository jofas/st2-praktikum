package ms3restspringdemo.factories;

import java.util.Collection;

import org.springframework.stereotype.Component;

import ms3restspringdemo.entities.Gericht;
import ms3restspringdemo.entities.Speise;


@Component
public class GerichtFactory {
	
	// Erstelle ein Gericht, das nur aus einer Speise besteht.
	public static Gericht createGerichtWithSpeise(String name, String details, double preis, Speise speise) {
		Gericht gericht = new Gericht(name,details, preis);
		gericht.addSpeise(speise);
		// Rueckreferenz setzen
		speise.addGericht(gericht);
		return gericht;
	}
	
	// Erstelle ein Gericht, das aus mehreren Speisen besteht.
	public static Gericht createGerichtWithSpeisen(String name, String details, double preis, Collection<Speise> speisen) {
		Gericht gericht = new Gericht(name,details, preis);
		gericht.addSpeisen(speisen);
		for(Speise s : speisen) {
			// Rueckreferenz setzen
			s.addGericht(gericht);
		}
		return gericht;
	}
}
