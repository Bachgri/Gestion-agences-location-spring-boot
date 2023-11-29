package api.location.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import api.location.entity.Droit;
import api.location.repository.DroitRepo;
import api.location.service.DroitService;

@Service
public class DroitServiceImpl implements DroitService {

	DroitRepo repo;
	
	
	public DroitServiceImpl(DroitRepo repo) { 
		this.repo = repo;
	}

	@Override
	public List<Droit> all() { 
		return repo.findAll();
	}

	@Override
	public Droit get(Long id) { 
		return repo.findById(id).get();
	}

	@Override
	public Droit post(Droit droit) { 
		return repo.save(droit);
	}

	@Override
	public Droit put(Droit droit) { 
		return repo.save(droit);
	}

	@Override
	public Droit delete(Long id) { 
		return null;
	}
	
}
