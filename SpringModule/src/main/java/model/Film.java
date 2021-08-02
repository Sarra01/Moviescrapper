package model;

import java.util.Iterator;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Film")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Film_seq")
	@SequenceGenerator(name = "Film_seq", sequenceName = "Film_seq")
	private long idFilm;
	private String nameFilm;
	private String linkPapystreaming = "";
	private int idCategory;
	private int year;
	private String language;
	private int numberRate;
	private int currentRate;

	@OneToMany(mappedBy = "film")
	Set<CategoFilm> categories;

	@OneToMany(mappedBy = "film")
	Set<ActFilm> actors;

	@OneToMany(mappedBy = "film")
	Set<Favourite> fav;

	@OneToMany(mappedBy = "filmRefUserInfoRefFB")
	Set<FeedBack> fb;
	
	 public Set<CategoFilm> getCategories() {
			return categories;
		}

		public void setCategories(Set<CategoFilm> categories) {
			this.categories = categories;
		}

		public Set<ActFilm> getActors() {
			return actors;
		}

		public void setActors(Set<ActFilm> actors) {
			this.actors = actors;
		}

		public void addCategory(Category category) {
	        CategoFilm categoFilm = new CategoFilm(this, category);
	        categories.add(categoFilm);
	        category.getFilms().add(categoFilm);
	    }
	 
	    public void removeCategory(Category category) {
	        for (Iterator<CategoFilm> iterator = categories.iterator();
	             iterator.hasNext(); ) {
	            CategoFilm categoFilm = iterator.next();
	 
	            if (categoFilm.getFilm().equals(this) &&
	                    categoFilm.getCategory().equals(category)) {
	                iterator.remove();
	                categoFilm.getCategory().getFilms().remove(categoFilm);
	                categoFilm.setFilm(null);
	                categoFilm.setCategory(null);
	            }
	        }
	    }
		public void addActor(Actor actor) {
	        ActFilm actFilm = new ActFilm(actor, this);
	        actors.add(actFilm);
	        actor.getFilms().add(actFilm);
	    }
	 
	    public void removeActor(Actor actor) {
	        for (Iterator<ActFilm> iterator = actors.iterator();
	             iterator.hasNext(); ) {
	            ActFilm actFilm = iterator.next();
	 
	            if (actFilm.getFilm().equals(this) &&
	                    actFilm.getActor().equals(actor)) {
	                iterator.remove();
	                actFilm.getActor().getFilms().remove(actFilm);
	                actFilm.setFilm(null);
	                actFilm.setActor(null);
	            }
	        }
	    }
		


		public long getIdFilm() {
			return idFilm;
		}

		public void setIdFilm(long idFilm) {
			this.idFilm = idFilm;
		}

		public String getNameFilm() {
			return nameFilm;
		}

		public void setNameFilm(String nameFilm) {
			this.nameFilm = nameFilm;
		}

		public String getLinkPapystreaming() {
			return linkPapystreaming;
		}

		public void setLinkPapystreaming(String linkPapystreaming) {
			this.linkPapystreaming = linkPapystreaming;
		}

		public int getIdCategory() {
			return idCategory;
		}

		public void setIdCategory(int idCategory) {
			this.idCategory = idCategory;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public int getNumberRate() {
			return numberRate;
		}

		public void setNumberRate(int numberRate) {
			this.numberRate = numberRate;
		}

		public int getCurrentRate() {
			return currentRate;
		}

		public void setCurrentRate(int currentRate) {
			this.currentRate = currentRate;
		}

}
