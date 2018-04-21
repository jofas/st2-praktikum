package ms1jpaspringdemo.entities;

import javax.persistence.*;

@Entity
public class Speise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
		
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
