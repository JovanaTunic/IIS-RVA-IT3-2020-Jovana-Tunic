package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.KorisnikUsluga;


public interface KorisnikUslugaRepository extends JpaRepository<KorisnikUsluga,Integer> {
	
	List<KorisnikUsluga> findByImeContainingIgnoreCase(String ime);   
	 
	// *****************getKorisnikUslugaByPocetakImena*********************************
	@Query(value="select * from korisnik_usluga where lower(ime) like ?1%",nativeQuery=true)   
		public abstract List<KorisnikUsluga> getKorisnikUslugaByPocetakImena(@Param("pocetak")String pocetakImena);

}
