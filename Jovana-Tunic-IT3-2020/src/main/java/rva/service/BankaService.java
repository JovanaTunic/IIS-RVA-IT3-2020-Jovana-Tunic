package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import rva.model.Banka;
import rva.repository.BankaRepository;

//u servisima se odvija logika
@Service
public class BankaService {

	@Autowired
	private BankaRepository bankaRepository;
	
	//Kreiramo metodu koja vraca sve banke
	//Ne moramo da imamo parametara
	//Imamo implementaciju
	public List<Banka> getAllBanka()
	{
		 return bankaRepository.findAll();
	}
	
	public Optional <Banka> getBankaById(int bankaId){
		return bankaRepository.findById(bankaId);
	}
	
	public boolean existsById(int id) {

		return getBankaById(id).isPresent();
	}	
	
	public List<Banka> getAllBankaByNaziv(String naziv){
		return bankaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Banka> getBankaByPocetakNaziva (String pocetakNaziva){
		return bankaRepository.getBankaByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
	public Banka addBanka (Banka banka) 
	{
	    return bankaRepository.save(banka);	
	}
	
	public void deleteById (int id)
	{
	    bankaRepository.deleteById(id);
	}
}

