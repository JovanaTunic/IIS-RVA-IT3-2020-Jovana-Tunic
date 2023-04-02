package rva.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) // da se ne vraca ta stavka u json fajl
@Table(name="Usluga")
public class Usluga implements Serializable  {

	private static final long serialVersionUID=1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USLUGA_ID_GENERATOR")
		@SequenceGenerator(name="USLUGA_ID_GENERATOR", sequenceName = "USLUGA_SEQ", allocationSize = 1)
		private int id;
		
		//znaci column jer su razdvojene reci!
		private String naziv;
		
		@Column(name="opis_usluge")
		private String opisUsluge;
		
		@Column(name="datum_ugovora")
		private Date datumUgovora;
		
		private double provizija;
		
		@ManyToOne
		@OnDelete(action=OnDeleteAction.CASCADE)
		@JoinColumn(name="filijala")
		private Filijala filijala;
		
		@ManyToOne
		@OnDelete(action=OnDeleteAction.CASCADE)
		@JoinColumn(name="korisnik_usluga")
		private KorisnikUsluga korisnikUsluga;
		
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

		public String getOpisUsluge() {
			return opisUsluge;
		}

		public void setOpisUsluge(String opisUsluge) {
			this.opisUsluge = opisUsluge;
		}

		public Date getDatumUgovora() {
			return datumUgovora;
		}

		public void setDatumUgovora(Date datumUgovora) {
			this.datumUgovora = datumUgovora;
		}

		public double getProvizija() {
			return provizija;
		}

		public void setProvizija(double provizija) {
			this.provizija = provizija;
		}

		public Filijala getFilijala() {
			return filijala;
		}

		public void setFilijala(Filijala filijala) {
			this.filijala = filijala;
		}

		public KorisnikUsluga getKorisnikUsluga() {
			return korisnikUsluga;
		}

		public void setKorisnikUsluga(KorisnikUsluga korisnikUsluga) {
			this.korisnikUsluga = korisnikUsluga;
		}

		
		
		

}
