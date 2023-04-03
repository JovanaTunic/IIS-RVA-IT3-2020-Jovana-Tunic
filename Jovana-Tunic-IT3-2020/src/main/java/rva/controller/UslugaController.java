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

import rva.model.Filijala;
import rva.model.Usluga;
import rva.service.FilijalaService;
import rva.service.UslugaService;

@RestController
public class UslugaController {
	
	@Autowired
	private UslugaService uslugaService;
	@Autowired
	private FilijalaService filijalaService;
	//@Autowired
//	private KorisnikUslugaService korisnikService;
	
	@GetMapping("/usluga") 
    public ResponseEntity<?> getAllUsluga()
	{
		 List<Usluga> usluga = uslugaService.getAllUsluga();
		 if(usluga.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista usluga je prazna");
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(usluga);
	}

	@GetMapping ("/usluga/{id}")
	public ResponseEntity<?> getUslugaById(@PathVariable("id") int id)
	{
		if(uslugaService.existsById(id)) {
		Optional<Usluga> usluga = uslugaService.getUslugaById(id);
		return ResponseEntity.ok(usluga);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usluga sa id-jem" + id + "ne postoji");
	}
	
	@GetMapping("/usluga/naziv/{naziv}") 
    public ResponseEntity<?> getAllUslugaByNaziv(@PathVariable("naziv")String naziv)
	{
		 List<Usluga> usluga = uslugaService.getUslugaByNaziv(naziv);
		 
		 if(usluga.isEmpty()) {
			 return new ResponseEntity<>("Lista uslgua je prazna",HttpStatus.NOT_FOUND);
		 }
    	 return new ResponseEntity<>(usluga,HttpStatus.OK);
	}
	
	@GetMapping("/usluga/pocetakNaziva/{pocetakNaziva}") 
	public ResponseEntity<?> getAllUslugaByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva)
		{
			 List<Usluga> usluga = uslugaService.getUslugaByPocetakNaziva(pocetakNaziva);
			 
			 if(usluga.isEmpty()) {
				 return new ResponseEntity<>("Lista usluga je prazna",HttpStatus.NOT_FOUND);
			 }
	    	 return new ResponseEntity<>(usluga,HttpStatus.OK);
		}
	
	@GetMapping("usluga/UslugaFilijala/{id}")
	public ResponseEntity<?> getAllForFilijala(@PathVariable("id") int id) {
		Optional<Filijala> filijala=filijalaService.getFilijalaById(id);
		if(filijala.isPresent()) {
			List<Usluga> usluga=uslugaService.findByFilijala(filijala.get());
			if(usluga.isEmpty()) {
				return new ResponseEntity<>("Filijala u datoj usluzi ne postoji!",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(usluga,HttpStatus.OK);
		}
		return new ResponseEntity<>("Filijala nije pronadjena",HttpStatus.OK);
			
	}
	
	@PostMapping("/usluga")
	public ResponseEntity<?> postUsluga(@RequestBody Usluga usluga){
		if(uslugaService.existsById(usluga.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usluga sa id-jem " + usluga.getId() + " vec postoji");
		}
		Usluga savedUsluga = uslugaService.addUsluga(usluga);
   	  return new ResponseEntity<>(savedUsluga,HttpStatus.OK);

	} 

    @PutMapping("/usluga/{id}")   
    public ResponseEntity<?> putUsluga(@PathVariable("id") int id,
		@RequestBody Usluga usluga){
	if(!uslugaService.existsById(usluga.getId())) {
		return new ResponseEntity<>("Usluga sa id-jem " + usluga.getId() + " nije pronadjena",HttpStatus.NOT_FOUND);
	}
	usluga.setId(id);
	Usluga updatedUsluga = uslugaService.addUsluga(usluga);
	  return new ResponseEntity<>(updatedUsluga,HttpStatus.OK);

    } 



    @DeleteMapping ("/usluga/{id}")
    public ResponseEntity<?> deleteUsluga(@PathVariable("id") int id){
	if(!uslugaService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usluga sa id-jem  " + id + " ne postoji");
	}
	
	uslugaService.deleteById(id);
	return new ResponseEntity<>("Usluga sa id-jem " + id + " je obrisana",HttpStatus.OK);

	}
}
