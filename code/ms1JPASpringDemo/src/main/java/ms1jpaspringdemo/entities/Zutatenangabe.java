package ms1jpaspringdemo.entities;

import javax.persistence.*;

@Embeddable
public class Zutatenangabe {
	private int menge;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Zutat zutat;
	
	// Hier muss ein Default-Konstrukor hin, weil eine Zutatenangabe bevor der Zutat erstellt werden muss
	public Zutatenangabe() {};
	
	public Zutatenangabe(Zutat zutat, int menge) {
		this.zutat = zutat;
		this.menge = menge;
	}
	
	@Override
	public String toString() {
		return "Zutat: " + zutat.toString() + "   Menge: " + menge;
	}
}
