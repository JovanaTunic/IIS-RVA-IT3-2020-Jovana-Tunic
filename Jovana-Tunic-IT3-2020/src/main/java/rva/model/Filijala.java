package rva.model;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Filijala implements Serializable  {
	
private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILIJALA_ID_GENERATOR")
	@SequenceGenerator(name="FILIJALA_ID_GENERATOR", sequenceName = "FILIJALA_SEQ", allocationSize = 1)
	private int id;
	
	private String adresa;
	
	@Column(name="broj_pultova")
	private Integer brojPultova;
	
	@Column(name="poseduje_sef")
	private Boolean posedujeSef;
	
	@ManyToOne   // vise banki za jednu filijalu - jedna banka moze biti sadrzana u vise filijali
	@OnDelete(action=OnDeleteAction.CASCADE)
	
	@JoinColumn(name="banka")  // kolona banka ce da sadrzi banku koja je povezana sa tom filajlom
	private Banka banka;
	
	// --------------------getters and setters---------------------------//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Integer getBrojPultova() {
		return brojPultova;
	}

	public void setBrojPultova(Integer brojPultova) {
		this.brojPultova = brojPultova;
	}

	public Boolean getPosedujeSef() {
		return posedujeSef;
	}

	public void setPosedujeSef(Boolean posedujeSef) {
		this.posedujeSef = posedujeSef;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	

}
