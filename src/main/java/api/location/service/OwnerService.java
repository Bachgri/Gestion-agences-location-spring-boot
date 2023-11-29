package api.location.service;

import java.util.List;

import api.location.entity.Owner;

public interface OwnerService {
	public List<Owner> all();
	public Owner get(Long id);
	public Owner post(Owner o);
	public Owner put(Owner o);
	public Owner delete(Long id);
	public Owner loadByOwnername(String u);
}
