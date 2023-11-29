package api.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.Droit;

public interface DroitRepo extends JpaRepository<Droit	, Long> {
	
	public Droit findByName(String name);
}
