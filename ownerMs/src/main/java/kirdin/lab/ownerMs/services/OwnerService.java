package kirdin.lab.ownerMs.services;

import kirdin.lab.models.jpa.Owner;
import kirdin.lab.ownerMs.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@ComponentScan(basePackages = "kirdin.lab.dal")
public class OwnerService {
    private final OwnerRepository ownerRepository;
    public List<Owner> getAll(){
        return ownerRepository.findAll();
    }

    public List<Owner> findByName(String name){
        return ownerRepository.findAllByName(name);
    }

    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner save(Owner owner){
        return ownerRepository.saveAndFlush(owner);
    }

    public void delete(Long id){
        ownerRepository.deleteById(id);
    }
}