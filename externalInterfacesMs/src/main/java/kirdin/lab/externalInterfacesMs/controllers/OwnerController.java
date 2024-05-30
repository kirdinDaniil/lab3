package kirdin.lab.externalInterfacesMs.controllers;

import kirdin.lab.externalInterfacesMs.producers.OwnerProducer;
import kirdin.lab.externalInterfacesMs.producers.util.MassageUtil;
import kirdin.lab.externalInterfacesMs.service.OwnerService;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.dto.OwnerResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.services")
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OwnerResponse>> getAll(){
        return new ResponseEntity<>(ownerService.getAll().getList(), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ownerService.findById(id);
    }

    @GetMapping("/owners/{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<OwnerResponse>> getByName(@PathVariable("name") String name){
        return new ResponseEntity<>(ownerService.findByName(name).getList(), HttpStatus.OK);
    }

    @PostMapping("/owner")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> newOwner(@RequestBody OwnerRequest ownerRequest){
        ownerService.save(ownerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/owner")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> persistOwner(@RequestBody OwnerRequest ownerRequest){
        ownerService.save(ownerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/owner/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id){
        ownerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}