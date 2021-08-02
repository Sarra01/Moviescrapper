package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CategoFilm {

	@EmbeddedId
	CategoFilmKey id;

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	Film filmRefCat;

	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "category_id")
	Category category;

	int rating;

}
