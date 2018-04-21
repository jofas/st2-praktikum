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
	
	// Die Anleitung enthält mehrere Zutatenangaben als Value-Objects.
	@ElementCollection (targetClass = Zutatenangabe.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "ZUTATENANGABE")
	private Set<Zutatenangabe> angaben = new HashSet<Zutatenangabe>();
	
	// Auch diesen Default-Konstruktor erzwingt JPA aus irgendeinem Grund
	public Zubereitungsanleitung() {};
	
	public Zubereitungsanleitung(String anleitung) {
		this.anleitung = anleitung;
	}
	
	public Zubereitungsanleitung(String anleitung, Collection<Zutatenangabe> angb) {
		this.anleitung = anleitung;
		this.angaben = new HashSet<Zutatenangabe>(angb);
	}
	
	// Ist es wirklich notwendig eine Angabe nur einzeln hinzuzufügen??
	public void addZutatenangabe(Zutatenangabe angabe) {
		angaben.add(angabe);
	}
	
	public void addZutatenangaben(Collection<Zutatenangabe> angb) {
		this.angaben.addAll(angb);
	}
	
	public Set<Zutatenangabe> getZutatenangaben() {
		return Collections.unmodifiableSet(angaben);
	}
	
	@Override
	public String toString() {
		return
		"Text: " + anleitung + "\n" +
		String.join("\n", angaben.stream().map(Object::toString).toArray(String[]::new));
	}
}
