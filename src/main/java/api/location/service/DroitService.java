package api.location.service;

import java.util.List;

import api.location.entity.Droit;

public interface DroitService {

	public List<Droit> all();
	public Droit get(Long id);
	public Droit post(Droit droit);
	public Droit put(Droit droit);
	public Droit delete(Long id);
	
	
}
