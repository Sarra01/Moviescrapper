import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import model.Actor;
import repository.ActorRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ActorTest {

	@Autowired
	private ActorRepository repforTest;

	@Test
	@Rollback(false)
	void testAddActor() {
		Actor actor = new Actor();
		actor.setNameActor("Cara DeLevigne");
		Actor actorToAdd = repforTest.save(actor);
		long actorId = actor.getIdActor();
		assertEquals(actorToAdd.getIdActor(), actorId);
	}

	@Test
	@Rollback(false)
	void testGetActor() {

		Optional<Actor> act = repforTest.findById(52L);
		Actor actor = act.get();
		long actorid = actor.getIdActor();
		System.out.println(actorid);
		assertEquals(52L, actorid);
	}

	@Test
	@Rollback(false)
	void testGetActors() {
		List<Actor> l = repforTest.findAll();
		assertThat(l.size()).isGreaterThan(1);
	}

	@Test
	@Rollback(false)
	void testDeleteActor() {
		Optional<Actor> act = repforTest.findById(502L);
		Actor actor = act.get();
		long actid = actor.getIdActor();
		repforTest.deleteById(actid);
	}
}