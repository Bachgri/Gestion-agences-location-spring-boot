package api.location.service;

import java.util.List;

import api.location.entity.Utilisateur;

public interface UtilisateurService {
	public List<Utilisateur> all();
	public Utilisateur get(Long id);
	public Utilisateur post(Utilisateur u);
	public Utilisateur put(Utilisateur u);
	public Utilisateur delete(Long id);
	public Utilisateur loadByUsername(String u);
	public List<Utilisateur> loadByType(String u);
	
	
}
