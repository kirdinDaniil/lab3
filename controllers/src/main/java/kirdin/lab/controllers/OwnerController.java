package kirdin.lab.controllers;

import kirdin.lab.dal.models.Owner;
import kirdin.lab.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.services")
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAll(){
        return new ResponseEntity<>(ownerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(ownerService.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Owner with id %d not found", id)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/owners/{name}")
    public ResponseEntity<List<Owner>> getByName(@PathVariable("name") String name){
        return new ResponseEntity<>(ownerService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/owner")
    public ResponseEntity<Owner> newOwner(@RequestBody Owner owner){
        return new ResponseEntity<>(ownerService.save(owner), HttpStatus.OK);
    }

    @PutMapping("/owner/{id}")
    public ResponseEntity<Owner> persistOwner(@RequestBody Owner newOwner, @PathVariable("id") Long id){
            return new ResponseEntity<>(ownerService.findById(id)
                    .map(
                            owner -> {
                                owner.setBirthdate(newOwner.getBirthdate());
                                owner.setName(newOwner.getName());
                                return ownerService.save(owner);
                            }
                    ).orElseGet(
                            () -> {
                                newOwner.setId(id);
                                return ownerService.save(newOwner);
                            }
                    ), HttpStatus.OK);

    }


    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        ownerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
