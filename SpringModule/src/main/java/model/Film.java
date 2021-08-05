package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String linkChill = "";
	private String linkMoviestars = "";
	private String linkPrimeware = "";
	private String year;
	private String numberRate;
	private String currentRate;
	
	
	@ManyToMany
    @JoinTable(name = "actfilm", joinColumns =  @JoinColumn(name = "id_film"), inverseJoinColumns = @JoinColumn(name = "id_actor"))
	private Set<Actor> actors = new HashSet<>();
	

	@ManyToMany
    @JoinTable(name = "catfilm", joinColumns =  @JoinColumn(name = "id_film"), inverseJoinColumns = @JoinColumn(name = "id_category"))
	private Set<Category> categories = new HashSet<>();


	@ManyToMany
	@JoinTable(name = "langfilm", joinColumns =  @JoinColumn(name = "id_film"), inverseJoinColumns = @JoinColumn(name = "id_language"))
	private Set<Language> languages = new HashSet<>();
	

	@OneToMany(mappedBy = "filmRefUserInfoRefFav")
	Set<Favourite> fav;

	@OneToMany(mappedBy = "filmRefUserInfoRefFB")
	Set<FeedBack> fb;

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

	public String getLinkChill() {
		return linkChill;
	}

	public void setLinkChill(String linkChill) {
		this.linkChill = linkChill;
	}

	public String getLinkMoviestars() {
		return linkMoviestars;
	}

	public void setLinkMoviestars(String linkMoviestars) {
		this.linkMoviestars = linkMoviestars;
	}

	public String getLinkPrimeware() {
		return linkPrimeware;
	}

	public void setLinkPrimeware(String linkPrimeware) {
		this.linkPrimeware = linkPrimeware;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNumberRate() {
		return numberRate;
	}

	public void setNumberRate(String numberRate) {
		this.numberRate = numberRate;
	}

	public String getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(String currentRate) {
		this.currentRate = currentRate;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public Set<Favourite> getFav() {
		return fav;
	}

	public void setFav(Set<Favourite> fav) {
		this.fav = fav;
	}

	public Set<FeedBack> getFb() {
		return fb;
	}

	public void setFb(Set<FeedBack> fb) {
		this.fb = fb;
	}
	
	
	
	
}
