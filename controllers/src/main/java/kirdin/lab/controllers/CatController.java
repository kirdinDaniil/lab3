package kirdin.lab.controllers;

import kirdin.lab.dal.models.Cat;
import kirdin.lab.dal.models.CatResponse;
import kirdin.lab.dal.models.Color;
import kirdin.lab.dal.models.Owner;
import kirdin.lab.services.CatService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.services")
@RestController
public class CatController {
    private final CatService catService;

    @GetMapping("/cats")
    public ResponseEntity<List<CatResponse>> getAll(){
        return new ResponseEntity<>(castCats(catService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(new CatResponse(catService.findById(id).orElseThrow()), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Cat with id %d not found", id)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cat_coloring/{color}")
    public ResponseEntity<List<CatResponse>> getByColoring(@PathVariable("color") Color color){
        return new ResponseEntity<>(castCats(catService.findAllByColoring(color)), HttpStatus.OK);
    }

    @GetMapping("/cat_coloring/{name}")
    public ResponseEntity<List<CatResponse>> getByName(@PathVariable("name") String name){
        return new ResponseEntity<>(castCats(catService.findAllByName(name)), HttpStatus.OK);
    }

    @GetMapping("/cat_coloring/{breed}")
    public ResponseEntity<List<CatResponse>> getByColoring(@PathVariable("breed") String breed){
        return new ResponseEntity<>(castCats(catService.findAllByBread(breed)), HttpStatus.OK);
    }

    @PostMapping("/cat")
    public ResponseEntity<?> newOwner(@RequestBody Cat cat){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cat/{id}")
    public ResponseEntity<Cat> persistOwner(@RequestBody Cat newCat, @PathVariable("id") Long id){
        return new ResponseEntity<>(catService.findById(id)
                .map(
                        cat -> {
                            cat.setOwner(newCat.getOwner());
                            cat.setBirthdate(newCat.getBirthdate());
                            cat.setBreed(newCat.getBreed());
                            cat.setName(newCat.getName());
                            cat.setColoring(newCat.getColoring());
                            return catService.save(cat);
                        }
                ).orElseGet(
                        () -> {
                            newCat.setId(id);
                            return catService.save(newCat);
                        }
                ), HttpStatus.OK);

    }

    @PutMapping("/cat_relation/{id1}/{id2}")
    public ResponseEntity<?> makeFriend(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2){
        try {
            catService.makeFriends(catService.findById(id1).orElseThrow(), catService.findById(id2).orElseThrow());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Cats with id %d and id %d not found", id1, id2)), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cat/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<CatResponse> castCats(List<Cat> cats){
        List<CatResponse> catResponses = new ArrayList<>();
        for (Cat cat : cats){
            catResponses.add(new CatResponse(cat));
        }
        return catResponses;
    }
}
