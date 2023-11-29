package api.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.Utilisateur;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
	public Utilisateur findByUsername(String username);

	public List<Utilisateur> findByType(String u);
}
