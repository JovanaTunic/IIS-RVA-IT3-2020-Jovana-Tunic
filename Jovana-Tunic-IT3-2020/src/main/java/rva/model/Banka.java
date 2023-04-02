package rva.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity // na osnovu ove anotacije prepoznaje klasu i kreira tabelu u bazi
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Banka implements Serializable {
	
private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANKA_ID_GENERATOR")
	@SequenceGenerator(name="BANKA_ID_GENERATOR", sequenceName = "BANKA_SEQ", allocationSize = 1)
	private int id;
	
	private String naziv;
	
	private String kontakt;
	
	private Integer pib;
	
	@JsonIgnore
	@OneToMany(mappedBy = "banka")
	private List<Filijala> filijala;
	
	// --------------------getters and setters---------------------------//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public Integer getPib() {
		return pib;
	}

	public void setPib(Integer pib) {
		this.pib = pib;
	}

	public List<Filijala> getFilijala() {
		return filijala;
	}

	public void setFilijala(List<Filijala> filijala) {
		this.filijala = filijala;
	}
	
	
	

}
