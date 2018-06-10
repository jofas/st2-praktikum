package ms1jpaspringdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms1jpaspringdemo.entities.Gericht;

public interface GerichtRepository extends CrudRepository<Gericht,Integer> {
	
	List<Gericht> findByPreisLessThan(double preis);
}
