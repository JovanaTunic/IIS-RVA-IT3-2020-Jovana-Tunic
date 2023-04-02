package rva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Banka;
import rva.service.BankaService;

@RestController
public class BankaController {

	@Autowired
	private BankaService bankaService;

	@GetMapping("/banka")  // da mapira get metodu
    public ResponseEntity<?> getAllBanka()
	{
		 List<Banka> banka = bankaService.getAllBanka();
		 if(banka.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista banki je prazna");
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(banka);
	}
	
	@GetMapping ("/banka/{id}")
	public ResponseEntity<?> getBankaById(@PathVariable("id") int bankaId)
	{
		if(bankaService.existsById(bankaId)) {
		Optional<Banka> banka = bankaService.getBankaById(bankaId);
		return ResponseEntity.ok(banka);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banka sa id-jem" + bankaId + "ne postoji");
	}
	
	@GetMapping("/banka/naziv/{naziv}")  // da mapira get metodu
    public ResponseEntity<?> getAllArtikliByNaziv(@PathVariable("naziv")String naziv)  //mora path variable
	{
		 List<Banka> banka = bankaService.getAllBankaByNaziv(naziv);
		 
		 if(banka.isEmpty()) {
			 return new ResponseEntity<>("Lista banki je prazna",HttpStatus.NOT_FOUND);
		 }
    	 return new ResponseEntity<>(banka,HttpStatus.OK);
	}
	
	@GetMapping("/banka/pocetakNaziva/{pocetakNaziva}") 
	public ResponseEntity<?> getAllArtikliByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva)  //mora path variable
		{
			 List<Banka> banka = bankaService.getBankaByPocetakNaziva(pocetakNaziva);
			 
			 if(banka.isEmpty()) {
				 return new ResponseEntity<>("Lista banki je prazna",HttpStatus.NOT_FOUND);
			 }
	    	 return new ResponseEntity<>(banka,HttpStatus.OK);
		}
	
	//putanja ne mora da se menja ako menjamo zahtev   -- za vrednost id-a nemamo sekvencu
	@PostMapping("/banka")
		public ResponseEntity<?> postBanka(@RequestBody Banka banka){
			//prvo kreiramo na servisu metodu -- banka service
			if(bankaService.existsById(banka.getId())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banka sa id-jem " + banka.getId() + " vec postoji");
			}
			Banka savedBanka = bankaService.addBanka(banka);
	   	 return new ResponseEntity<>(savedBanka,HttpStatus.OK);

		} 
	
	@PutMapping("/banka/{id}")   //kad radimo put treba nam id jer zelimo da kazemo koji menjamo
	public ResponseEntity<?> putBanka(@PathVariable("id") int bankaId,
			@RequestBody Banka banka){
		//prvo kreiramo na servisu metodu -- artikl service
		if(!bankaService.existsById(banka.getId())) {
			return new ResponseEntity<>("Banka sa id-jem " + banka.getId() + " nije pronadjena",HttpStatus.NOT_FOUND);
		}
		banka.setId(bankaId);
		Banka updatedBanka = bankaService.addBanka(banka);
   	  return new ResponseEntity<>(updatedBanka,HttpStatus.OK);

	} 
	
	//za post nam treba objekat (telo)
	
	@DeleteMapping ("/banka/{id}")
	public ResponseEntity<?> deleteBanka(@PathVariable("id") int bankaId){
		if(!bankaService.existsById(bankaId)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banka sa id-jem  " + bankaId + " ne postoji");
		}
		
		bankaService.deleteById(bankaId);
		return new ResponseEntity<>("Banka sa id-jem " + bankaId + " je obrisana",HttpStatus.OK);

		}
}
