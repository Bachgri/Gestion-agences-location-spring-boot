package api.location.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Identifiant (ID)
 *  Nom
 * 	Prénom
 *	Adresse e-mail
 * 	Mot de passe (haché et sécurisé)
 *	Rôle (par exemple, utilisateur normal, administrateur)
 * 
 * @author Oualid
 *
 *
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String permet;
	private String cin;
	@Column(columnDefinition = "varchar(32) default ''")
	private String ville;
	@Column(unique = true)
	private String username;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToOne
	Droit role;
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	List<Interfaces> interfaces = new ArrayList<>();
	private String type;
}
