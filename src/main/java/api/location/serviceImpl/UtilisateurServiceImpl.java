package api.location.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.location.entity.Utilisateur;
import api.location.repository.UtilisateurRepo;
import api.location.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	UtilisateurRepo repo;

	PasswordEncoder passwordEncoder;
	

	public UtilisateurServiceImpl(UtilisateurRepo repo, PasswordEncoder passwordEncoder) {
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<Utilisateur> all() { 
		return repo.findAll();
	}

	@Override
	public Utilisateur get(Long id) { 
		return repo.findById(id).get();
	}

	@Override
	public Utilisateur post(Utilisateur u) { 
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repo.saveAndFlush(u);
	}

	@Override
	public Utilisateur put(Utilisateur u) { 
		return repo.saveAndFlush(u);
	}

	@Override
	public Utilisateur delete(Long id) { 
		Utilisateur u = repo.findById(id).get();
		repo.delete(u);
		return u;
	}

	@Override
	public Utilisateur loadByUsername(String u) { 
		return repo.findByUsername(u);
	}

	@Override
	public List<Utilisateur> loadByType(String u) { 
		return repo.findByType(u);
	}

}
