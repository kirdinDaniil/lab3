package kirdin.lab.externalInterfacesMs.controllers;


import kirdin.lab.externalInterfacesMs.Observers.CatConsumerObserver;
import kirdin.lab.externalInterfacesMs.producers.CatProducer;
import kirdin.lab.externalInterfacesMs.producers.util.MassageUtil;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.jpa.Color;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.externalInterfacesMs")
@RestController
public class CatController {
    private final CatConsumerObserver catService;
    private final CatProducer catProducer;

    @GetMapping("/cats")
    public ResponseEntity<List<CatResponse>> getAll(){
        catProducer.sendMassage(MassageUtil.buildMessage("getAll",null));
        return catService.getCats();
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        catProducer.sendMassage(MassageUtil.buildMessage("getById",id));
        return catService.getCats();
    }

    @GetMapping("/cat_coloring/{color}")
    public ResponseEntity<List<CatResponse>> getByColoring(@PathVariable("color") Color color){
        catProducer.sendMassage(MassageUtil.buildMessage("getByColor", color));
        return catService.getCats();
    }

    @GetMapping("/cat_name/{name}")
    public ResponseEntity<List<CatResponse>> getByName(@PathVariable("name") String name){
        catProducer.sendMassage(MassageUtil.buildMessage("getByName", name));
        return catService.getCats();
    }

    @GetMapping("/cat_breed/{breed}")
    public ResponseEntity<List<CatResponse>> getByBreed(@PathVariable("breed") String breed){
        catProducer.sendMassage(MassageUtil.buildMessage("getByBreed", breed));
        return catService.getCats();
    }

    @PostMapping("/cat")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> newCat(@RequestBody CatRequest catRequest){
        catProducer.sendCat(catRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cat/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> persistCat(@RequestBody CatRequest catRequest, @PathVariable("id") Long id){
        catProducer.sendCat(catRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cat/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        catProducer.sendMassage(MassageUtil.buildMessage("deleteCat", id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}