import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import model.Actor;
import model.Admin;
import repository.AdminRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AdminTest {

	@Autowired
	private AdminRepository repforTest;

	@Test
	@Rollback(false)
	void testAddActor() {
		Admin admin = new Admin();
		admin.setEmail("administrator@yahoo.com");
		Admin adminToAdd = repforTest.save(admin);
		long adminId = admin.getIdAdmin();
		assertEquals(adminToAdd.getIdAdmin(), adminId);
	}

	@Test
	@Rollback(false)
	void testGetAdmin() {

		Optional<Admin> adm = repforTest.findById(52L);
		Admin admin = adm.get();
		//long actorid = actor.getIdActor();
		System.out.println(actorid);
		assertEquals(52L, actorid);
	}

	@Test
	@Rollback(false)
	void testGetAdmins() {
		List<Admin> list = repforTest.findAll();
		assertThat(list.size()).isGreaterThan(1);
	}

	@Test
	@Rollback(false)
	void testDeleteActor() {
		//Optional<Actor> act = repforTest.findById(502L);
		Actor actor = act.get();
		long actid = actor.getIdActor();
		repforTest.deleteById(actid);
	}
}