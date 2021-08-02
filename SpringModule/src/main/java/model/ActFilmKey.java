package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class ActFilmKey implements Serializable {

	@Column(name = "film_id")
	Long filmId;

	@Column(name = "actor_id")
	Long actorId;

}
