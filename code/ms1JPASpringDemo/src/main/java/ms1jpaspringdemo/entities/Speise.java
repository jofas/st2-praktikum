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
	
	public Speise(String name, Zubereitungsanleitung anleitung) {
		this.name = name;
		this.anleitung = anleitung;
	}
	
	@Override
	public String toString() {
		return
		"Speise: " + name + "\n" +
		"Zubereitungsanleitung: \n" + anleitung + "\n";
	}
}
