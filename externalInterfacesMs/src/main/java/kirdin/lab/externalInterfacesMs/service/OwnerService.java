package kirdin.lab.externalInterfacesMs.service;

import kirdin.lab.externalInterfacesMs.producers.OwnerProducer;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.dto.OwnerResponseList;
import kirdin.lab.models.jpa.Owner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerProducer ownerProducer;

    @Value("${http.owner.get.all}")
    private String urlGetAll;

    @Value("${http.owner.get.by.id}")
    private String urlGetById;

    @Value("${http.owner.get.by.name}")
    private String urlGetByName;

    @Value("${http.owner.delete}")
    private String urlDelete;

    public OwnerService(OwnerProducer ownerProducer) {
        this.ownerProducer = ownerProducer;
    }

    public OwnerResponseList getAll(){
        return new RestTemplate()
                .getForObject(urlGetAll, OwnerResponseList.class);
    }

    public OwnerResponseList findByName(String name){
        return new RestTemplate()
                .getForObject(urlGetByName, OwnerResponseList.class, name);
    }

    public ResponseEntity<?> findById(Long id) {
        return new RestTemplate()
                .getForObject(urlGetById, ResponseEntity.class, id);
    }

    public void save(OwnerRequest owner){
        ownerProducer.sendOwner(owner);
    }

    public void delete(Long id){
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.delete(urlDelete, id);
    }
}
