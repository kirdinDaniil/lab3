package kirdin.lab.ownerMs.controllers;

import kirdin.lab.models.dto.CatResponseList;
import kirdin.lab.models.dto.OwnerResponse;
import kirdin.lab.models.dto.OwnerResponseList;
import kirdin.lab.models.util.DtoUtil;
import kirdin.lab.ownerMs.services.OwnerService;
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
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public OwnerResponseList getAll(){
        return new OwnerResponseList(DtoUtil.castOwners(ownerService.getAll()));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(new OwnerResponse(ownerService.findById(id).orElseThrow()), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Owner with id %d not found", id)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/owners/{name}")
    public OwnerResponseList getByName(@PathVariable("name") String name){
        return new OwnerResponseList(DtoUtil.castOwners(ownerService.findByName(name)));
    }

    @DeleteMapping("/owner/{id}")
    public void deleteOwner(@PathVariable("id") Long id){
        ownerService.delete(id);
    }
}
