package model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "actor")
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_seq")
	@SequenceGenerator(name = "actor_seq", sequenceName = "actor_seq")
	private long idActor;
	private String nameActor;

	@OneToMany(mappedBy = "actor")
    Set<ActFilm> films;
	
	public Actor(long id,String name) 
	{
		this.idActor=id;
		this.nameActor=name;
	}
	public Actor() {}

	public long getIdActor() {
		return idActor;
	}

	public void setIdActor(long idActor) {
		this.idActor = idActor;
	}

	public String getNameActor() {
		return nameActor;
	}

	public void setNameActor(String nameActor) {
		this.nameActor = nameActor;
	}
	public Set<ActFilm> getFilms() {
		return films;
	}
	public void setFilms(Set<ActFilm> films) {
		this.films = films;
	}
	


}
