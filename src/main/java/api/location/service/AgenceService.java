package api.location.service;

import java.util.List;

import api.location.entity.RentalAgency;
import api.location.entity.Utilisateur;

public interface AgenceService {
	
	public List<RentalAgency> all();
	public RentalAgency get(Long id);
	public RentalAgency post(RentalAgency a);
	public RentalAgency put(RentalAgency a);
	public RentalAgency delete(Long id);
	public List<RentalAgency> loadByUser(long u);
}
