package kirdin.lab.externalInterfacesMs.controllers;


import kirdin.lab.externalInterfacesMs.Security.UserSecurityDetails;
import kirdin.lab.externalInterfacesMs.producers.util.MassageUtil;
import kirdin.lab.externalInterfacesMs.service.CatService;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.dto.CatResponseList;
import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Color;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.externalInterfacesMs")
@RestController
public class CatController {
    private final CatService catService;

    @GetMapping("/cats")
    public ResponseEntity<List<CatResponse>> getAll(){
        CatResponseList catResponseList = catService.getAll();
        return new ResponseEntity<>(filterCats(catResponseList), HttpStatus.OK);
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return catService.findById(id);
    }

    @GetMapping("/cat_coloring/{color}")
    public ResponseEntity<List<CatResponse>> getByColoring(@PathVariable("color") Color color){
        CatResponseList catResponseList = catService.findAllByColoring(color);
        return new ResponseEntity<>(filterCats(catResponseList), HttpStatus.OK);
    }

    @GetMapping("/cat_name/{name}")
    public ResponseEntity<List<CatResponse>> getByName(@PathVariable("name") String name){
        CatResponseList catResponseList = catService.findAllByName(name);
        return new ResponseEntity<>(filterCats(catResponseList), HttpStatus.OK);
    }

    @GetMapping("/cat_breed/{breed}")
    public ResponseEntity<List<CatResponse>> getByBreed(@PathVariable("breed") String breed){
        CatResponseList catResponseList = catService.findAllByBread(breed);
        return new ResponseEntity<>(filterCats(catResponseList), HttpStatus.OK);
    }

    @PostMapping("/cat")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> newCat(@RequestBody CatRequest catRequest){
        catService.save(catRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cat")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> persistCat(@RequestBody CatRequest catRequest){
        catService.save(catRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cat/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        catService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Authentication getCurrentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public List<CatResponse> filterCats(CatResponseList catResponses){
        catResponses.setList(getEnableCats(catResponses.getList()));
        return catResponses.getList();
    }

    public List<CatResponse> getEnableCats(List<CatResponse> cats){
        Authentication authentication = getCurrentAuthentication();
        if (authentication.equals(null))
            return cats;
        String authority = authentication.getAuthorities().stream().toList().get(0).getAuthority();
        if (authority.equals("ROLE_ADMIN"))
            return cats;

        UserSecurityDetails user = (UserSecurityDetails) authentication.getPrincipal();
        Long OwnerId = user.getOwnerId();

        return cats.stream().filter(cat -> cat.getOwnerId().equals(OwnerId)).toList();
    }

    public CatResponse getEnableCat(CatResponse cat){
        Authentication authentication = getCurrentAuthentication();
        if (authentication.equals(null))
            return cat;
        String authority = authentication.getAuthorities().stream().toList().get(0).getAuthority();

        UserSecurityDetails user = (UserSecurityDetails) authentication.getPrincipal();
        Long OwnerId = user.getOwnerId();

        if (authority.equals("ROLE_ADMIN") || cat.getOwnerId().equals(OwnerId))
            return cat;

        throw new NoSuchElementException();
    }

}