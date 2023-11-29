package api.location.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Identifiant (ID)
 * Nom de l'agence
 * Adresse
 * Numéro de téléphone
 * Adresse e-mail de contact
 * @author Oualid
 *
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class RentalAgency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String adresse;
	private String phone;
	private String email;
	@ManyToOne
	private Utilisateur owner;
	private String ville;
	
}
