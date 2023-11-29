package api.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.RentalAgency;
import api.location.entity.Voiture;

public interface VoitureRepo extends JpaRepository<Voiture, Long> {
	public List<Voiture> findByAgence(RentalAgency agence);
}
