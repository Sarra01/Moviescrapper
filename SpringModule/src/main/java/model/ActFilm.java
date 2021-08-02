package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ActFilm {

	@EmbeddedId
	ActFilmKey id;

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	Film film;

	@ManyToOne
	@MapsId("actorId")
	@JoinColumn(name = "actor_id")
	Actor actor;
	
	public ActFilm(Actor actor,Film film){
		this.actor=actor;
		this.film=film;
		this.id= new ActFilmKey(actor.getIdActor(),film.getIdFilm());
		
	}

	public ActFilmKey getId() {
		return id;
	}

	public void setId(ActFilmKey id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
