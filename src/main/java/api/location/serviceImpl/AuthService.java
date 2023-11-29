package api.location.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.location.entity.Droit;
import api.location.entity.Utilisateur;
import api.location.service.UtilisateurService;

@Service
public class AuthService implements UserDetailsService {

	UtilisateurService utilisateurService;
	PasswordEncoder passwordEncoder;
	
	
	public AuthService(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
		super();
		this.utilisateurService = utilisateurService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurService.loadByUsername(username);
		Droit droit = utilisateur.getRole();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(droit.getName()));
		return new User(username, utilisateur.getPassword(), grantedAuthorities);
	} 
	@Transactional
	public UserDetails loadOwnerByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = utilisateurService.loadByUsername(username);
		Droit droit = utilisateur.getRole();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(droit.getName()));
		return new User(username, utilisateur.getPassword(), grantedAuthorities);
	}
	public void saveUser(Utilisateur u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		utilisateurService.post(u);
	}
	
}
