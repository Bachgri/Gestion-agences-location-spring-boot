package api.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.AgencyBranch;
import api.location.entity.RentalAgency;
import api.location.entity.Utilisateur;

public interface AgenceRepo extends JpaRepository<RentalAgency, Long> {
	public List<RentalAgency> findByOwner(Utilisateur owner);
}
