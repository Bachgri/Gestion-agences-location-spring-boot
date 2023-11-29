package api.location;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import api.location.entity.Interfaces;
import api.location.entity.Utilisateur;
import api.location.repository.DroitRepo;
import api.location.repository.InterfaceRepo;
import api.location.service.AgenceService;
import api.location.service.UtilisateurService;

@SpringBootApplication
public class LocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationApplication.class, args);
	}

	@Bean
	CommandLineRunner start(DroitRepo fr, UtilisateurService ur, InterfaceRepo ir, AgenceService as) {
		return args -> {
			String server = "127.0.0.1"; // Server can be either host name or IP address.
			int port = 21;
			String user = "oualid";
			String pass = "20012022";

			FTPClient ftp = new FTPClient();
			ftp.connect(server, port);
			boolean a = ftp.login(user, pass);
			System.err.println("ftp connected : " + a);
			ftp.enterRemotePassiveMode();
			// System.err.println(ftp.listDirectories()+ "\n"+ftp.listFiles()+
			// "\n"+ftp.pwd());
			// System.err.println(fr.findAll());
			// System.err.println(as.loadByUser(28));
			/*
			 * Utilisateur u = ur.get(2L);
			 * u.setInterfaces(ir.findAll());
			 * ur.put(u);
			 */

//			ir.save(new Interfaces("", "fa-solid fa-house", "/voitures"));
//			ir.save(new Interfaces("Mes reservations", "fa-solid fa-house", "/mes_reservation"));
//			ir.save(new Interfaces("Clients", "fa-solid fa-house", "clients"));

			/*
			 */ 
			  Utilisateur u = new Utilisateur(null, "aze", "oualidlachgar7@gmail.com",
			  "0622115470", "aze", "aze", "aze", "aze", "aze", fr.findByName("ADMIN"),
			  ir.findAll(), "CLIENT");
			  ur.post(u);
			 /* Utilisateur u1 = new Utilisateur(null, "CLIENT2", "oualidlachgar7@gmail.com",
			  "0622115470", "B", "PA253549", "client2", "cient2", fr.findByName("ADMIN"),
			  ir.findAll(), "CLIENT");
			  ur.post(u1);
			 */
			// System.err.println(ur.findByUsername("bachgri"));
			// fr.post(new Droit(null, "ADMIN"));
			// fr.save(new Droit(null, "SUPERADMIN"));

			/*
			 * ArrayList<ClientRole> roles = new ArrayList<>();
			 * ClientRole role = new ClientRole("ADMIN");
			 * fr.addRole(role);
			 * roles.add(role);
			 * fr.addClient(new Client(null, "admin", "admin", "admin15963", "email",
			 * "0622115470", "",roles ));
			 */
		};
	}

}
