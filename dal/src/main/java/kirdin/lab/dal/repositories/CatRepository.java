package kirdin.lab.dal.repositories;

import kirdin.lab.dal.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByBreed(String breed);

    List<Cat> findAllByName(String name);

}