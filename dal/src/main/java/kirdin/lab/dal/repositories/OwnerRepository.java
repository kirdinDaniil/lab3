package kirdin.lab.dal.repositories;

import kirdin.lab.dal.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findAllByName(String name);
}
