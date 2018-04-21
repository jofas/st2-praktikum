package ms1jpaspringdemo.factories;

import java.util.Collection;

import org.springframework.stereotype.Component;

import ms1jpaspringdemo.entities.Gericht;
import ms1jpaspringdemo.entities.Speise;

/* Die Factory hier ist jetzt nicht so nützlich, da es keine Zurückreferenzen gibt und ein Gericht
 * auch nicht komplex konfiguriert werden muss.
 * Man könnte noch eine Methode hinzufügen, die Speisen hinzufügt. Oder man könnte auch eine Factory erstellen,
 * die über Method-Chaining funktioniert.
 * Diese Factory ist aber nur ganz einfach gehalten und da sie keinen Zustand verwaltet, sind die Mehoden static.
 */
@Component
public class GerichtFactory {
	
	// Kann ein Gericht wirklich nur aus einer Speise bestehen??
	// Erstelle ein Gericht, das nur aus einer Speise besteht.
	public static Gericht createGerichtWithSpeise(String name, String details, double preis, Speise speise) {
		Gericht gericht = new Gericht(name,details, preis);
		gericht.addSpeise(speise);
		return gericht;
	}
	
	// Erstelle ein Gericht, das aus mehreren Speisen besteht.
	public static Gericht createGerichtWithSpeisen(String name, String details, double preis, Collection<Speise> speisen) {
		Gericht gericht = new Gericht(name,details, preis);
		gericht.addSpeisen(speisen);
		return gericht;
	}
}
