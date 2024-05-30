package kirdin.lab.catMs.controllers;


import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.dto.CatResponseList;
import kirdin.lab.models.jpa.Color;
import kirdin.lab.models.util.DtoUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class CatController {
    private final CatService catService;

    @GetMapping("/cats")
    public CatResponseList getAll(){
        return new CatResponseList(DtoUtil.castCats(catService.getAll()));
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

    @GetMapping("/cat_by_coloring/{color}")
    public CatResponseList getByColoring(@PathVariable("color") Color color){
        return new CatResponseList(DtoUtil.castCats(catService.findAllByColoring(color)));
    }

    @GetMapping("/cat_by_name/{name}")
    public CatResponseList getByName(@PathVariable("name") String name){
        return new CatResponseList(DtoUtil.castCats(catService.findAllByName(name)));
    }

    @GetMapping("/cat_bt_breed/{breed}")
    public CatResponseList getByColoring(@PathVariable("breed") String breed){
        return new CatResponseList(DtoUtil.castCats(catService.findAllByBread(breed)));
    }

    @DeleteMapping("/delete_cat/{id}")
    public void deleteCat(@PathVariable("id") Long id){
        catService.delete(id);
    }
}
