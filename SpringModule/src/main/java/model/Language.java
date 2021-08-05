package model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Language")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Language_seq")
	@SequenceGenerator(name = "Language_seq", sequenceName = "Language_seq")
	private long idLanguage;
	private String nameLanguage;
	
	public long getIdLanguage() {
		return idLanguage;
	}
	public void setIdLanguage(long idLanguage) {
		this.idLanguage = idLanguage;
	}
	public String getNameLanguage() {
		return nameLanguage;
	}
	public void setNameLanguage(String nameLanguage) {
		this.nameLanguage = nameLanguage;
	}
	
    
}