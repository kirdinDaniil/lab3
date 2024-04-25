package kirdin.lab.dal.repositories;

import kirdin.lab.dal.models.Cat;
import kirdin.lab.dal.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByBreed(String breed);

    List<Cat> findAllByName(String name);

}
