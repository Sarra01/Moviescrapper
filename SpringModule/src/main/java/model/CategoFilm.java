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
	Film film;

	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "category_id")
	Category category;

	int rating;

	public CategoFilm(Film film, Category category) {
		super();
		this.film = film;
		this.category = category;
	}

	public CategoFilmKey getId() {
		return id;
	}

	public void setId(CategoFilmKey id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
