package api.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.location.entity.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

	public Owner findByUsername(String ownername);

}
