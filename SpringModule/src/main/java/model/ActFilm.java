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
	Film filmRefAct;

	@ManyToOne
	@MapsId("actorId")
	@JoinColumn(name = "actor_id")
	Actor actRefFilm;

}
