package api.location.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Interfaces {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String icon;
	private String url;
	public Interfaces(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}
	public Interfaces(String name) {
		super();
		this.name = name;
	}
	public Interfaces(String name, String icon, String url) {
		super();
		this.name = name;
		this.icon = icon;
		this.url = url;
	}
	
	
	
}
