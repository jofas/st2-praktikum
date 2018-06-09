package ms3restspringdemo.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Speise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	// bidirektionale Beziehung: Gericht kennt zugehoerige Speisen und die Speisen kennen zugehoerige Gerichte
	@JsonManagedReference
	@ManyToMany(mappedBy = "speisen")
	private Set<Gericht> gerichte = new HashSet<Gericht>();
	
	// Zubereitungsanleitung als Value Object
	@Embedded
	private Zubereitungsanleitung anleitung;
	
	// für JPA ist hier wieder ein Default-Konstruktor notwendig
	public Speise() {};
	
	public Speise(String name, Zubereitungsanleitung anleitung) {
		this.name = name;
		this.anleitung = anleitung;
	}
	
	public String getName() {
		return name;
	}
	
	public void addGericht(Gericht g) {
		gerichte.add(g);
	}
	
	public void addGerichte(Collection<Gericht> gerichte) {
		gerichte.addAll(gerichte);
	}
	
	@Override
	public String toString() {
		return
		"Speise: " + name + "\n" +
		"Zubereitungsanleitung: \n" + anleitung;
	}
}
