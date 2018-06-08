package ms2restspringdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms2restspringdemo.entities.Zutat;

public interface ZutatRepository extends CrudRepository<Zutat,Integer> {
	public List<Zutat> findByNameLike(String searchTerm);
}
