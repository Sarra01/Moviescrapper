package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Actor;


public interface ActorRepository extends JpaRepository<Actor, Long> {

}