package ms1jpaspringdemo.entities;

import java.util.Collection;
import java.util.Collections;
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
	@ElementCollection (targetClass = Zutatenmenge.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "ZUTATENANGABE")
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

	// Ist es wirklich notwendig eine Angabe nur einzeln hinzuzufügen??
	public void addZutatenangabe(Zutatenmenge angabe) {
		angaben.add(angabe);
	}

	public void addZutatenangaben(Collection<Zutatenmenge> angb) {
		this.angaben.addAll(angb);
	}

	public Set<Zutatenmenge> getZutatenangaben() {
		return Collections.unmodifiableSet(angaben);
	}

	@Override
	public String toString() {
		return
		"Text: " + anleitung + "\n" +
		String.join("\n", angaben.stream().map(Object::toString).toArray(String[]::new));
	}
}
