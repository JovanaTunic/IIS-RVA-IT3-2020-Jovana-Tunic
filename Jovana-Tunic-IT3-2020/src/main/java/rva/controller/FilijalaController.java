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
import rva.model.Filijala;
import rva.service.BankaService;
import rva.service.FilijalaService;

@RestController
public class FilijalaController  {

	@Autowired
	private FilijalaService filijalaService;
	@Autowired
	private BankaService bankaService;
	
	@GetMapping("/filijala") 
    public ResponseEntity<?> getAllFilijala()
	{
		 List<Filijala> filijala = filijalaService.getAllFilijala();
		 if(filijala.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista filijala je prazna");
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(filijala);
	}
	
	@GetMapping ("/filijala/{id}")
	public ResponseEntity<?> getFilijalaById(@PathVariable("id") int id)
	{
		if(filijalaService.existsById(id)) {
		Optional<Filijala> filijala = filijalaService.getFilijalaById(id);
		return ResponseEntity.ok(filijala);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filijala sa id-jem" + id + "ne postoji");
	}
	
	@GetMapping("/filijala/pocetakAdrese/{pocetakAdrese}") 
	public ResponseEntity<?> getFilijalaByPocetakImena(@PathVariable("pocetakAdrese")String pocetakAdrese)
		{
			 List<Filijala> filijala = filijalaService.getFilijalaByPocetakAdrese(pocetakAdrese);
			 
			 if(filijala.isEmpty()) {
				 return new ResponseEntity<>("Lista filijala je prazna",HttpStatus.NOT_FOUND);
			 }
	    	 return new ResponseEntity<>(filijala,HttpStatus.OK);
		}
	
	@GetMapping("filijala/filijalaBanke/{id}")
	public ResponseEntity<?> getAllForBanka(@PathVariable("id") int id) {
		Optional<Banka> banka=bankaService.getBankaById(id);
		if(banka.isPresent()) {
			List<Filijala> filijala=filijalaService.findByBanka(banka.get());
			if(filijala.isEmpty()) {
				return new ResponseEntity<>("Filijala u datoj banci ne postoji!",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(filijala,HttpStatus.OK);
		}
		return new ResponseEntity<>("Banka nije pronadjena",HttpStatus.OK);
			
	}
	
	@PostMapping("/filijala")
	public ResponseEntity<?> postFilijala(@RequestBody Filijala filijala){
		if(filijalaService.existsById(filijala.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filijala sa id-jem " + filijala.getId() + " vec postoji");
		}
		Filijala savedFilijala = filijalaService.addFilijala(filijala);
   	 return new ResponseEntity<>(savedFilijala,HttpStatus.OK);

	} 
	
	@PutMapping("/filijala/{id}") 
	public ResponseEntity<?> putFilijala(@PathVariable("id") int id,
			@RequestBody Filijala filijala){
		//prvo kreiramo na servisu metodu -- artikl service
		if(!filijalaService.existsById(filijala.getId())) {
			return new ResponseEntity<>("Filijala sa id-jem " + filijala.getId() + " nije pronadjena",HttpStatus.NOT_FOUND);
		}
		filijala.setId(id);
		Filijala updatedFilijala = filijalaService.addFilijala(filijala);
   	  return new ResponseEntity<>(updatedFilijala,HttpStatus.OK);

	} 
	
	@DeleteMapping ("/filijala/{id}")
	public ResponseEntity<?> deleteFilijala(@PathVariable("id") int id){
		if(!filijalaService.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filijala sa id-jem  " + id + " ne postoji");
		}
		
		filijalaService.deleteById(id);
		return new ResponseEntity<>("Filijala sa id-jem " + id + " je obrisana",HttpStatus.OK);

		}
	
	
	
}
