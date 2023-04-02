package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Banka;

public interface BankaRepository extends JpaRepository<Banka,Integer> {  //Drugi parametar je tip podatka primarnog kljuca
	
	// *****************getAllBankaByNaziv*********************************
	List<Banka> findByNazivContainingIgnoreCase(String naziv);   
 
	// *****************getAllBankaByPocetakNaziva*********************************
	@Query(value="select * from banka where lower(naziv) like ?1%",nativeQuery=true)   
		List<Banka> getBankaByPocetakNaziva(@Param("pocetak")String pocetakNaziva);
}
