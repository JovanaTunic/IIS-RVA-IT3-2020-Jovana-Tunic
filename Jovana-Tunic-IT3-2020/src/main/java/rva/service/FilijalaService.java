package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Banka;
import rva.model.Filijala;
import rva.repository.FilijalaRepository;

@Service
public class FilijalaService {
	
	@Autowired
	private FilijalaRepository filijalaRepository;

	public List<Filijala> getAllFilijala()
	{
		 return filijalaRepository.findAll();
	}
	
	public Optional <Filijala> getFilijalaById(int id){
		return filijalaRepository.findById(id);
	}
	
	public boolean existsById(int id) {

		return getFilijalaById(id).isPresent();
	}
	
	public List<Filijala> getFilijalaByPocetakAdrese (String pocetakAdrese){
		return filijalaRepository.getFilijalaByPocetakAdrese(pocetakAdrese.toLowerCase());
	}
	
	public List<Filijala> findByBanka(Banka banka){
		return filijalaRepository.findByBanka(banka);
	}
	
	public Filijala addFilijala (Filijala filijala) 
	{
	    return filijalaRepository.save(filijala);	
	}

	
	public void deleteById (int id)
	{
		filijalaRepository.deleteById(id);
	}
	
	
}
