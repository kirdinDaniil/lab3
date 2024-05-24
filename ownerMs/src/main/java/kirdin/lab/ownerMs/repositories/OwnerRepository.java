package kirdin.lab.ownerMs.repositories;

import kirdin.lab.models.jpa.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findAllByName(String name);
}