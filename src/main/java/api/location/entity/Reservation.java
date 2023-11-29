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
 *  Identifiant (ID)
 * 	Utilisateur (clé étrangère liant la réservation à l'utilisateur)
 * 	Voiture (clé étrangère liant la réservation à la voiture)
 * 	Agence de Location (clé étrangère liant la réservation à l'agence)
 * 	Succursale d'Agence (clé étrangère liant la réservation à la succursale)
 * 	Date de début de la réservation
 * 	Date de fin de la réservation
 *  Prix total de la réservation
 *  État de la réservation (par exemple, en attente de confirmation, confirmée, terminée)
 *  @author Oualid
 *
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Voiture voiture;
	@ManyToOne
	private RentalAgency agence;
	@ManyToOne
	private AgencyBranch branch;
	private String dd;
	private String df;
	private double prix;
	private String status;
	
	
}
