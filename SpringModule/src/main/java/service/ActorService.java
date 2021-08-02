package service;

import java.util.List;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Actor;
import repository.ActorRepository;


@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepository;

	public List<Actor> getAll() {
		return actorRepository.findAll();
	}

	public Actor get(long id) {
		return actorRepository.getOne(id);
	}

	public void delete(long id) {
		actorRepository.deleteById(id);
	}

	public Actor Add(Actor actor) {
		return actorRepository.save(actor);

	}

}
