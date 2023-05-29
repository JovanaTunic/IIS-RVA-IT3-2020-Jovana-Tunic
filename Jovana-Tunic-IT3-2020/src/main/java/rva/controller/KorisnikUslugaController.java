package rva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Banka;
import rva.model.KorisnikUsluga;
import rva.service.BankaService;
import rva.service.KorisnikUslugaService;

@CrossOrigin
@RestController
public class KorisnikUslugaController {

	@Autowired
	private KorisnikUslugaService korisnikUslugaService;
	
	@Autowired
	private JdbcTemplate template;

	@GetMapping("/korisnikUsluga")  
    public ResponseEntity<?> getAllKorisnikUsluga()
	{
		 List<KorisnikUsluga> korisnikUsluga = korisnikUslugaService.getAllKorisnikUsluga();
		 if(korisnikUsluga.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista korisnika je prazna");
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(korisnikUsluga);
	}
	
	@GetMapping ("/korisnikUsluga/{id}")
	public ResponseEntity<?> getKorisnikUslugaById(@PathVariable("id") int id)
	{
		if(korisnikUslugaService.existsById(id)) {
		Optional<KorisnikUsluga> korisnikUsluga = korisnikUslugaService.getKorisnikUslugaById(id);
		return ResponseEntity.ok(korisnikUsluga);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik sa id-jem" + id + "ne postoji");
	}
	
	
	@GetMapping("/korisnikUsluga/ime/{ime}") 
    public ResponseEntity<?> getAllKorisnikUslugaByIme(@PathVariable("ime")String ime) 
	{
		 List<KorisnikUsluga> korisnikUsluga = korisnikUslugaService.getAllKorisnikUslugaByIme(ime);
		 
		 if(korisnikUsluga.isEmpty()) {
			 return new ResponseEntity<>("Lista korisnika je prazna",HttpStatus.NOT_FOUND);
		 }
    	 return new ResponseEntity<>(korisnikUsluga,HttpStatus.OK);
	}
	
	@GetMapping("/korisnikUsluga/pocetakImena/{pocetakImena}") 
	public ResponseEntity<?> getKorisnikUslugaByPocetakImena(@PathVariable("pocetakImena")String pocetakImena)
		{
			 List<KorisnikUsluga> korisnikUsluga = korisnikUslugaService.getKorisnikUslugaByPocetakImena(pocetakImena);
			 
			 if(korisnikUsluga.isEmpty()) {
				 return new ResponseEntity<>("Lista korisnika je prazna",HttpStatus.NOT_FOUND);
			 }
	    	 return new ResponseEntity<>(korisnikUsluga,HttpStatus.OK);
		}
	

	@PostMapping("/korisnikUsluga")
		public ResponseEntity<?> postKorisnikUsluga(@RequestBody KorisnikUsluga korisnikUsluga){
		if(korisnikUslugaService.existsById(korisnikUsluga.getId())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik sa id-jem " + korisnikUsluga.getId() + " vec postoji");
			}
			KorisnikUsluga savedKorisnikUsluga = korisnikUslugaService.addKorisnikUsluga(korisnikUsluga);
	   	 return new ResponseEntity<>(savedKorisnikUsluga,HttpStatus.OK);

		} 

	
	@PutMapping("/korisnikUsluga/{id}") 
	public ResponseEntity<?> putKorisnikUsluga(@PathVariable("id") int id,
			@RequestBody KorisnikUsluga korisnikUsluga){
		if(!korisnikUslugaService.existsById(korisnikUsluga.getId())) {
			return new ResponseEntity<>("Korisnik sa id-jem " + korisnikUsluga.getId() + " nije pronadjena",HttpStatus.NOT_FOUND);
		}
		korisnikUsluga.setId(id);
		KorisnikUsluga updatedKorisnikUsluga = korisnikUslugaService.addKorisnikUsluga(korisnikUsluga);
   	  return new ResponseEntity<>(updatedKorisnikUsluga,HttpStatus.OK);

	} 
	
	
	@DeleteMapping ("/korisnikUsluga/{id}")
	public ResponseEntity<?> deleteKorisnikUsluga(@PathVariable("id") int id){
		if(!korisnikUslugaService.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik sa id-jem  " + id + " ne postoji");
		}
		else {
			if(id==-100) {
				korisnikUslugaService.deleteById(id);
				template.execute("INSERT INTO \"korisnik_usluga\" (\"id\", \"ime\", \"prezime\",\"maticni_broj\") VALUES(-100, 'Arija', 'Stefanovic','1254896523025')");
				return new ResponseEntity<>("Korisnik sa id-jem " + id + " je obrisan",HttpStatus.OK);
			}
			else {
				korisnikUslugaService.deleteById(id);
				return new ResponseEntity<>("Korisnik sa id-jem " + id + " je obrisan",HttpStatus.OK);
			}
		}
		
	}	
}
