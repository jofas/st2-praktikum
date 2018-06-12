package ms3restspringdemo.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  scope = Gericht.class)
public class Gericht {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String details;
	private double preis;
	
	// Ein Gericht besteht aus mehreren Speisen und eine Speise kann mehreren Gerichten zugeordnet sein.
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "gericht_speise",
		joinColumns = @JoinColumn(name = "gericht_id"),
		inverseJoinColumns = @JoinColumn(name = "speise_id")
	)
	private Set<Speise> speisen = new HashSet<Speise>();
	
	public Gericht() {};
	
	public Gericht(String name, String details, double preis) {
		this.name = name;
		this.details = details;
		this.preis = preis;
	}
	
	public Gericht(String name, String details, double preis, Collection<Speise> speisen) {
		this(name, details,preis);
		this.speisen = new HashSet<Speise>(speisen);
	}
	
	public void addSpeise(Speise speise) {
		speisen.add(speise);
	}
	
	public void addSpeisen(Collection<Speise> sp) {
		speisen.addAll(sp);
	}
	
	public void removeSpeise(Speise speise) {
		speisen.remove(speise);
	}
	
	@JsonProperty
	public Set<Speise> getSpeisen() {
		return Collections.unmodifiableSet(speisen);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}


	public double getPreis() {
		return preis;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public void setSpeisen(Set<Speise> speisen) {
		speisen.stream().forEach(sp -> sp.addGericht(this));
		this.speisen = speisen;
	}

	@Override
	public String toString() {
		return 
		"Gericht: " + name + "\n" +
		"Preis: " + preis + " €\n" +
		"Details: " + details + "\n" +
		"Anleitung: \n" +
		String.join("\n",speisen.stream().map(Speise::toString).toArray(String[]::new));
	}
	
}
