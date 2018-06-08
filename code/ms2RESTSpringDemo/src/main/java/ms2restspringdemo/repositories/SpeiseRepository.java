package ms2restspringdemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ms2restspringdemo.entities.Speise;
import ms2restspringdemo.entities.Zutat;

public interface SpeiseRepository extends CrudRepository<Speise,Integer> {
	// Die Abfrage ist in JPQL geschrieben - Eine objektorientierte Abfragesprache, welche SQL aehnlich ist
	// Findet alle Speisen, die eine bestimmte Zutat enthalten
	@Query("select s from Speise s join s.anleitung a join a.angaben ang where ang.zutat = :zutat")
	List<Speise> findByContainsZutat(@Param("zutat")Zutat zutat);	
}

