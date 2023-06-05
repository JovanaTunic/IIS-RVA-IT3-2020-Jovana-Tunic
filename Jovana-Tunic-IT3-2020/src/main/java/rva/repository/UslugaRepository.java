

package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Filijala;
import rva.model.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga,Integer>{
	
	List<Usluga> findByNazivContainingIgnoreCase(String naziv);   
	 
	@Query(value="select * from usluga where lower(naziv) like ?1%",nativeQuery=true)   
		List<Usluga> getUslugaByPocetakNaziva(@Param("pocetak")String pocetakNaziva);
	
	List<Usluga> findByFilijala(Filijala filijala);

}
