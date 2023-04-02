package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.KorisnikUsluga;
import rva.repository.KorisnikUslugaRepository;


@Service
public class KorisnikUslugaService {

	@Autowired
	private KorisnikUslugaRepository korisnikUslugaRepository;

	public List<KorisnikUsluga> getAllKorisnikUsluga()
	{
		 return korisnikUslugaRepository.findAll();
	}

	public Optional <KorisnikUsluga> getKorisnikUslugaById(int id){
		return korisnikUslugaRepository.findById(id);
	}
	
	public boolean existsById(int id) {

		return getKorisnikUslugaById(id).isPresent();
	}	
	
	
	public List<KorisnikUsluga> getAllKorisnikUslugaByIme(String ime)
	{
		return korisnikUslugaRepository.findByImeContainingIgnoreCase(ime);
	}
	/*
	public List<KorisnikUslugaService> getKorisnikUslugaByPocetakImena (String pocetakImena){
		return korisnikUslugaRepository.getBankaByPocetakNaziva(pocetakImena.toLowerCase());
	}
	*/
	public KorisnikUsluga addKorisnikUsluga (KorisnikUsluga korisnikUsluga) 
	{
	    return korisnikUslugaRepository.save(korisnikUsluga);	
	}
	
	public void deleteById (int id)
	{
		korisnikUslugaRepository.deleteById(id);
	}
	
}
