package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Filijala;
import rva.model.Usluga;
import rva.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;

	public List<Usluga> getAllUsluga()
	{
		 return uslugaRepository.findAll();
	}
	
	public Optional <Usluga> getUslugaById(int id){
		return uslugaRepository.findById(id);
	}
	
	public boolean existsById(int id) {

		return getUslugaById(id).isPresent();
	}
	
	
	public List<Usluga> getUslugaByNaziv(String naziv){
		return uslugaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Usluga> getUslugaByPocetakNaziva (String pocetakNaziva){
		return uslugaRepository.getUslugaByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
	public Usluga addUsluga (Usluga usluga) 
	{
	    return uslugaRepository.save(usluga);	
	}
	
	public void deleteById (int id)
	{
	    uslugaRepository.deleteById(id);
	}
	
	public List<Usluga> findByFilijala(Filijala filijala){
		return uslugaRepository.findByFilijala(filijala);
	}
	
}
