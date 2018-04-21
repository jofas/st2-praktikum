package ms1jpaspringdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import ms1jpaspringdemo.entities.Zutat;

public interface ZutatRepository extends CrudRepository<Zutat,Integer> {

}
