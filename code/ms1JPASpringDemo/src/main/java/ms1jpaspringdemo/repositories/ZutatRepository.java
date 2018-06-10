package ms1jpaspringdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms1jpaspringdemo.entities.Zutat;

public interface ZutatRepository extends CrudRepository<Zutat,Integer> {
	public List<Zutat> findByNameLike(String searchTerm);
}
