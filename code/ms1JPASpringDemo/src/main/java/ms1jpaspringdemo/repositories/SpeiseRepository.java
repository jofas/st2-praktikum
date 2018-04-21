package ms1jpaspringdemo.repositories;

import org.springframework.data.repository.CrudRepository;

import ms1jpaspringdemo.entities.Speise;

public interface SpeiseRepository extends CrudRepository<Speise,Integer> {

}

