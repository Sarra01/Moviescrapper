package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class CategoFilmKey implements Serializable {

	@Column(name = "film_id")
	Long filmId;

	@Column(name = "category_id")
	Long categoryId;

}
