package api.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.Interfaces;

public interface InterfaceRepo extends JpaRepository<Interfaces, Long> {
	public Interfaces findByName(String name);
}
