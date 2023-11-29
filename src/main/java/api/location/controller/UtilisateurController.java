package api.location.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.location.entity.Interfaces;
import api.location.entity.RentalAgency;
import api.location.entity.Utilisateur;
import api.location.repository.InterfaceRepo;
import api.location.service.AgenceService;
import api.location.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/location")

public class UtilisateurController {
	
	UtilisateurService service;
	InterfaceRepo interfaceRepo;
	AgenceService aservice;
	
	public UtilisateurController(UtilisateurService service, InterfaceRepo interfaceRepo, AgenceService aservice) {
		super();
		this.service = service;
		this.interfaceRepo = interfaceRepo;
		this.aservice = aservice;
	}
	
	@GetMapping("/users/{id}")
	public Utilisateur get(@PathVariable("id") Long id) {
		return service.get(id);
	}
	@GetMapping("/users")
	public List<Utilisateur> get() {
		return service.all();
	}	
	@DeleteMapping("/users/{id}")
	public Utilisateur delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
	@PostMapping("/users")
	public Utilisateur post(@RequestBody Utilisateur u) {
		System.err.println(u);
		return service.post(u);
	}
	@PutMapping("/users")
	public Utilisateur put(@RequestBody Utilisateur u) {
		Utilisateur uo = service.get(u.getId());
		return service.put(u);
	}
	@GetMapping("/users/{id}/interfaces")
	public ResponseEntity<List<Interfaces>> interfaces(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.get(id).getInterfaces());
	}
	@GetMapping("/users/{id}/agences")
	public ResponseEntity<List<RentalAgency>> agences(@PathVariable("id") Long id){
		System.err.println("ok2");
		return ResponseEntity.ok(aservice.loadByUser(id));
	}
	@GetMapping("/clients_type")
	public List<Utilisateur> loadClients(@RequestParam("type") String type){
		System.err.println(type);
		return service.loadByType(type);
	}
	@GetMapping("/users/interfaces")
	public ResponseEntity<List<Interfaces>> allInterfaces(HttpServletRequest request) throws Exception{
		System.err.println("this request coms from : " + request.getRemoteAddr()); 
		
		return ResponseEntity.ok(interfaceRepo.findAll());
	}
}
