package ms2restspringdemo.factories;
import java.util.Collection;

import ms2restspringdemo.entities.Zubereitungsanleitung;
import ms2restspringdemo.entities.Zutatenmenge;

public class ZubereitungsanleitungFactory {
	
	/* Die Factory hier ist jetzt nicht so nützlich, da es keine Zurückreferenzen gibt und eine Zubereitungsanleitung
	 * auch nicht komplex konfiguriert werden muss.
	 * Man könnte noch eine Methode hinzufügen, die Angaben hinzufügt. Oder man könnte auch eine Factory erstellen,
	 * die über Method-Chaining funktioniert.
	 * Diese Factory ist aber nur ganz einfach gehalten und da sie keinen Zustand verwaltet, ist die Methode static.
	 */
	public static Zubereitungsanleitung createZubereitungsanleitungWithAngaben(String anleitungsText, Collection<Zutatenmenge> angaben) {
		Zubereitungsanleitung anleitung = new Zubereitungsanleitung(anleitungsText);
		anleitung.addZutatenangaben(angaben);
		return anleitung;
	}
}
