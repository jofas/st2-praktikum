package ms3restspringdemo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ms3restspringdemo.entities.Zutat;

public interface ZutatRepository extends CrudRepository<Zutat,Integer> {
	public List<Zutat> findByNameLike(String searchTerm);
}
