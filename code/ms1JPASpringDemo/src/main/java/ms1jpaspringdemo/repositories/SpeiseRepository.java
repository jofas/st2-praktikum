package ms1jpaspringdemo.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ms1jpaspringdemo.entities.Speise;
import ms1jpaspringdemo.entities.Zutat;

public interface SpeiseRepository extends CrudRepository<Speise,Integer> {

	// Die Abfrage ist in JPQL geschrieben - Eine objektorientierte Variante von SQL
	// Findet alle Speisen, die eine bestimmte Zutat enthalten
	@Query("select s from Speise s join s.anleitung a join a.angaben ang where ang.zutat = :zutat")
	List<Speise> findByContainsZutat(@Param("zutat")Zutat zutat);
	
}

