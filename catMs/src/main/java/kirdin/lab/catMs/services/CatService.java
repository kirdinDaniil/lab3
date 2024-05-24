package kirdin.lab.catMs.services;

import kirdin.lab.catMs.repositories.CatRepository;
import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Color;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@ComponentScan(basePackages = "kirdin.lab.dal")
public class CatService {
    private final CatRepository catRepository;

    public List<Cat> getAll(){
        return catRepository.findAll();
    }

    public Optional<Cat> findById(Long id) {
        return catRepository.findById(id);
    }

    public  List<Cat> findAllByName(String name){
        return catRepository.findAllByName(name);
    }

    public List<Cat> findAllByBread(String breed){
        return catRepository.findAllByBreed(breed);
    }

    public List<Cat> findAllByColoring(Color color){
        return catRepository.findAll().stream().filter( cat -> cat.getColoring().equals(color)).collect(Collectors.toList());
    }

    public Cat save(Cat cat){
        return catRepository.save(cat);
    }

    public void delete(Long id){
        catRepository.deleteById(id);
    }

    public void makeFriends(Cat cat1, Cat cat2){
        cat1.addFriend(cat2);
        save(cat1);
        save(cat2);
    }
}