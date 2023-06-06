package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.KorisnikUsluga;


public interface KorisnikUslugaRepository extends JpaRepository<KorisnikUsluga,Integer> { //model koji posmatramo i tip ID-ja
	
	List<KorisnikUsluga> findByImeContainingIgnoreCase(String ime);    
	 
	// *****************getKorisnikUslugaByPocetakImena*********************************
	@Query(value="select * from korisnik_usluga where lower(ime) like ?1%",nativeQuery=true)   
		public abstract List<KorisnikUsluga> getKorisnikUslugaByPocetakImena(@Param("pocetak")String pocetakImena);
	
	
	//metode koje ne postoje vec definisane u jpa repo jer su po obelezjima (koji je tip podatka uobicajni)
	//saved vec postoji negde u JPA repozitorijumu
	//postoji konvencija pisanja metoda ...

	

}
