package api.location.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import api.location.entity.Owner;
import api.location.repository.OwnerRepo;
import api.location.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	OwnerRepo repo;
	

	public OwnerServiceImpl(OwnerRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Owner> all() {
		return repo.findAll();
	}

	@Override
	public Owner get(Long id) { 
		return repo.findById(id).get();
	}

	@Override
	public Owner post(Owner o) { 
		return repo.save(o);
	}

	@Override
	public Owner put(Owner o) { 
		return repo.save(o);
	}

	@Override
	public Owner delete(Long id) {
		Owner o = get(id);
		repo.delete(o);
		return o;
	}

	@Override
	public Owner loadByOwnername(String u) { 
		return repo.findByUsername(u);
	}

	 
	
}
