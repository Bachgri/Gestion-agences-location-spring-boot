package api.location.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.location.entity.RentalAgency;
import api.location.entity.Utilisateur;
import api.location.repository.AgenceRepo;
import api.location.service.AgenceService;
import api.location.service.UtilisateurService;

@Service
public class AgenceServiceImpl implements AgenceService {

	@Autowired
	private AgenceRepo repo;
	@Autowired
	private UtilisateurService repou;
	
	@Override
	public List<RentalAgency> all() { 
		return repo.findAll();
	}

	@Override
	public RentalAgency get(Long id) { 
		return repo.findById(id).get();
	}

	@Override
	public RentalAgency post(RentalAgency a) { 
		return repo.saveAndFlush(a);
	}

	@Override
	public RentalAgency put(RentalAgency a) { 
		return repo.saveAndFlush(a);
	}

	@Override
	public RentalAgency delete(Long id) { 
		RentalAgency a = get(id);
		repo.delete(a);
		return a;
	}

	@Override
	public List<RentalAgency> loadByUser(long u) { 
		return repo.findByOwner(repou.get(u));
	}

	
	
}
