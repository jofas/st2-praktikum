package ms3restspringdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms3restspringdemo.entities.Gericht;

public interface GerichtRepository extends CrudRepository<Gericht,Integer> {
	
	List<Gericht> findByPreisLessThan(double preis);
}
