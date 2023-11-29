package api.location.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.location.entity.Droit;
import api.location.entity.Request;
import api.location.entity.Response;
import api.location.entity.Utilisateur;
import api.location.exception.DisabledUserException;
import api.location.exception.InvalidUserCredentialsException;
import api.location.jwt.JwtUtil;
import api.location.repository.DroitRepo;
import api.location.repository.InterfaceRepo;
import api.location.service.DroitService;
import api.location.service.UtilisateurService;
import api.location.serviceImpl.AuthService; 
 
 

@RestController
@CrossOrigin(origins = "https://*") 
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UtilisateurService userService;
	@Autowired
	private UtilisateurService ownerService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	InterfaceRepo ir;
	
	@Autowired 
	DroitRepo ds;
	
	@PostMapping("/login")
	public ResponseEntity<Response> generateJwtToken(@RequestBody Request request) throws Exception {
		org.springframework.security.core.Authentication authentication = null;
		System.out.println("resuest : " + request.getUsername());
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}

		User user =  (User) authentication.getPrincipal();
		List<Droit> roles = new ArrayList<>();
		
		//((org.springframework.security.core.Authentication) user).getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
		user.getAuthorities().forEach(r->{
			roles.add(new Droit(r.getAuthority()));
		});
		String token = jwtUtil.generateToken(authentication);
		Response response = new Response();
		response.setToken(token);
		
		response.setRole(roles.get(0).getName());
		response.setUserid(userService.loadByUsername(user.getUsername()).getId());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/owner/login")
	public ResponseEntity<Response> generateJwtTokenOwner(@RequestBody Request request) throws Exception {
		org.springframework.security.core.Authentication authentication = null;
		System.out.println("resuest : " + request.getUsername());
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (DisabledException e) {
			throw new DisabledUserException("User Inactive");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid Credentials");
		}

		User user =  (User) authentication.getPrincipal();
		List<Droit> roles = new ArrayList<>();
		
		//((org.springframework.security.core.Authentication) user).getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
		user.getAuthorities().forEach(r->{
			roles.add(new Droit(r.getAuthority()));
		});
		String token = jwtUtil.generateToken(authentication);
		Response response = new Response();
		response.setToken(token);
		
		response.setRole(roles.get(0).getName());
		response.setUserid(userService.loadByUsername(user.getUsername()).getId());
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<Utilisateur> signup(@RequestBody Utilisateur request) {
		request.setRole(ds.findByName("USER"));
		//request.setInterfaces(request.getInterfaces().add(ir.findByName("Mes Reserva")))
		return ResponseEntity.ok(userService.post(request));
	}
}
