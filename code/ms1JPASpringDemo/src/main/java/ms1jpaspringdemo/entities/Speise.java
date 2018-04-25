package ms1jpaspringdemo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Speise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	// bidirektionale Beziehung: Gericht kennt zugehörige Speisen und die Speisen kennen zugehörige Gerichte
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
	
	@Override
	public String toString() {
		return
		"Speise: " + name + "\n" +
		"Zubereitungsanleitung: \n" + anleitung;
	}
}
