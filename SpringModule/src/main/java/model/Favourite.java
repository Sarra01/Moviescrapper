package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Favourite {

	@EmbeddedId
	favouriteKey id;

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	Film filmRefUserInfoRefFav;

	@ManyToOne
	@MapsId("userInfoId")
	@JoinColumn(name = "userInfo_id")
	UserInfo userInfoRefFilmRefFav;

}
