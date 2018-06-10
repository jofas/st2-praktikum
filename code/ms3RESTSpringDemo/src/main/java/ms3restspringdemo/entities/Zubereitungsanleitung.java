package ms3restspringdemo.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

@Embeddable
public class Zubereitungsanleitung {
	private String anleitung;

	// Die Anleitung enthaelt mehrere Zutatenangaben als Value-Objects
	@CollectionTable(name = "Zutatenposition")
	@ElementCollection (targetClass = Zutatenmenge.class, fetch = FetchType.EAGER)
	private Set<Zutatenmenge> angaben = new HashSet<Zutatenmenge>();

	// Auch diesen Default-Konstruktor erzwingt JPA aus irgendeinen Grund
	public Zubereitungsanleitung() {};

	public Zubereitungsanleitung(String anleitung) {
		this.anleitung = anleitung;
	}

	public Zubereitungsanleitung(String anleitung, Collection<Zutatenmenge> angb) {
		this.anleitung = anleitung;
		this.angaben = new HashSet<Zutatenmenge>(angb);
	}
	
	public String getAnleitung() {
		return anleitung;
	}
	
	public void setAnleitung(String anleitung) {
		this.anleitung = anleitung;
	}

	public void addZutatenangabe(Zutatenmenge angabe) {
		angaben.add(angabe);
	}

	public void addZutatenangaben(Collection<Zutatenmenge> angb) {
		this.angaben.addAll(angb);
	}

	public Set<Zutatenmenge> getZutatenangaben() {
		return angaben;
	}

	@Override
	public String toString() {
		return
		"Text: " + anleitung + "\n" +
		String.join("\n", angaben.stream().map(Object::toString).toArray(String[]::new));
	}
}
