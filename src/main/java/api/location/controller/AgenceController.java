package api.location.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.location.entity.RentalAgency;
import api.location.service.AgenceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location/agences")
public class AgenceController {

	AgenceService service;
	
	
	public AgenceController(AgenceService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<RentalAgency> post(@RequestBody RentalAgency a){
		return ResponseEntity.ok(service.post(a));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<RentalAgency> delete(@PathVariable("id") long id){
		return ResponseEntity.ok(service.delete(id));
	}
	@PutMapping
	public ResponseEntity<RentalAgency> put(@RequestBody RentalAgency agence){
		return ResponseEntity.ok(service.put(agence));
	}
	
}
