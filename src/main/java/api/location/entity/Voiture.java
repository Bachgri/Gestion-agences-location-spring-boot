package api.location.entity;

import javax.persistence.Column;
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
 *  Marque
 *  Modèle
 *  Année de fabrication
 *  Catégorie (par exemple, compacte, berline, SUV, etc.)
 *  Prix de location par jour
 *	Disponibilité (indicateur si la voiture est disponible ou réservée)
 *  @author Oualid
 *
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Voiture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marque;
	private String model;
	private String anfab;
	private String categorie;
	private double prix;
	private String imgurl;
	private boolean disponible = true;
	@ManyToOne
	private RentalAgency agence;
	private int nbpalces;
	private int nbbagage;
}
