package ms1jpaspringdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import ms1jpaspringdemo.entities.Gericht;

public interface GerichtRepository extends CrudRepository<Gericht,Integer> {

}
