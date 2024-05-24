package kirdin.lab.catMs.repositories;

import kirdin.lab.models.jpa.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByBreed(String breed);

    List<Cat> findAllByName(String name);

}