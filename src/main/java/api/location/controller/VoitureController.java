package api.location.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.location.entity.Voiture;
import api.location.service.VoitureService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location/voitures")
public class VoitureController {

	@Autowired
	VoitureService service;

	@GetMapping
	public ResponseEntity<List<Voiture>> all(){
		return ResponseEntity.ok(service.all());
	}
	// 0661505288
	@PostMapping
	public ResponseEntity<Voiture> post(@RequestBody Voiture v){
		return ResponseEntity.ok(service.post(v)); 
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Voiture> delete(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.delete(id));
	}
	@GetMapping("/{id}/agence")
	public ResponseEntity<List<Voiture>> byAgence(@PathVariable("id") long id){
		return ResponseEntity.ok(service.loadByAgence(id));
	}
	@PostMapping("/upload") //iis
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("recid") String recid){
		System.err.println("ok3\n"+recid); 
		return ResponseEntity.ok(service.upload(file, recid));
	}
	
	
}
