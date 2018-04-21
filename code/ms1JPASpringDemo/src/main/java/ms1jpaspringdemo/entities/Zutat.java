package ms1jpaspringdemo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Zutat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	public Zutat(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override 
	public String toString(){
		return name;
	}
}
