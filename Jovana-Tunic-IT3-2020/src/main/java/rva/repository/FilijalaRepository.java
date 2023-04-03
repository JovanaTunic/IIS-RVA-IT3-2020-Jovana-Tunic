package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Banka;
import rva.model.Filijala;

public interface FilijalaRepository extends JpaRepository<Filijala,Integer>{

	@Query(value="select * from filijala where lower(adresa) like ?1%",nativeQuery=true)   
	public abstract List<Filijala> getFilijalaByPocetakAdrese(@Param("pocetak")String pocetakAdrese);

	List<Filijala> findByBanka(Banka banka);
	
	
}
