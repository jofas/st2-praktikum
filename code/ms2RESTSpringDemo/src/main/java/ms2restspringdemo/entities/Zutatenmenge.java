package ms2restspringdemo.entities;

import javax.persistence.*;

@Embeddable
public class Zutatenmenge {
	private int menge;
	
	@ManyToOne
	private Zutat zutat;
	
	// Hier muss ein Default-Konstrukor hin, weil eine Zutatenangabe bevor der Zutat erstellt werden muss
	public Zutatenmenge() {};
	
	public Zutatenmenge(Zutat zutat, int menge) {
		this.zutat = zutat;
		this.menge = menge;
	}
	
	@Override
	public String toString() {
		return "Zutat: " + String.format("%-10s Anzahl: %-3d", zutat, menge);
	}
}
