package ms3restspringdemo.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ms3restspringdemo.serializers.CustomSetSerializer;



@Entity
public class Speise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	// bidirektionale Beziehung: Gericht kennt zugehoerige Speisen und die Speisen kennen zugehoerige Gerichte
	@JsonIgnore // beim Erstellen einer Speise muessen die Gerichte nicht angegeben sein
	@ManyToMany(mappedBy = "speisen")
	private Set<Gericht> gerichte = new HashSet<Gericht>();
	
	// Zubereitungsanleitung als Value Object
	@JsonIgnore
	@Embedded
	private Zubereitungsanleitung anleitung;
	
	// für JPA ist hier wieder ein Default-Konstruktor notwendig
	public Speise() {};
	
	public Speise(String name, Zubereitungsanleitung anleitung) {
		this.name = name;
		this.anleitung = anleitung;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonIgnore
	public Zubereitungsanleitung getZubereitungsanleitung() {
		return anleitung;
	}
	
	// Gerichte die Speise beinhalten sollen zurueckgegeben werden
	@JsonProperty
	@JsonSerialize(using = CustomSetSerializer.class)
	public Set<Gericht> getGerichte() {
		return Collections.unmodifiableSet(gerichte);
	}
	
	public void setGerichte(Set<Gericht> gerichte) {
		this.gerichte = gerichte;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAnleitung(Zubereitungsanleitung anleitung) {
		this.anleitung = anleitung;
	}
		
	public void addGericht(Gericht g) {
		gerichte.add(g);
	}
	
	public void removeGericht(Gericht g) {
		gerichte.remove(g);
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
