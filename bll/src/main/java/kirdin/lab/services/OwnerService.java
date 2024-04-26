package kirdin.lab.services;

import kirdin.lab.dal.models.Owner;
import kirdin.lab.dal.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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