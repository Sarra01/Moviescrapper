package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
	Actor findByNameActor(String name);
	

}