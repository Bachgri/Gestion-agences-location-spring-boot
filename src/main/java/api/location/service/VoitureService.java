package api.location.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.location.entity.Voiture;
 

public interface VoitureService {

	public List<Voiture> all();
	public Voiture get(Long id);
	public Voiture post(Voiture v);
	public Voiture put(Voiture v);
	public Voiture delete(Long id);
	public List<Voiture> loadByAgence(long a);
	public ResponseEntity<byte[]> download(long recid);
	public String upload(MultipartFile file, String recid);

}
