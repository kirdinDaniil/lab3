package kirdin.lab.externalInterfacesMs.service;


import kirdin.lab.externalInterfacesMs.producers.CatProducer;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.CatResponseList;
import kirdin.lab.models.jpa.Color;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatService {
    @Value("${http.cat.get.all}")
    private String urlGetAll;

    @Value("${http.cat.get.by.id}")
    private String urlGetById;

    @Value("${http.cat.get.by.color}")
    private String urlGetByColor;

    @Value("${http.cat.get.by.name}")
    private String urlGetByName;

    @Value("${http.cat.get.by.breed}")
    private String urlGetByBreed;

    @Value("${http.cat.delete}")
    private String urlDelete;

    private final CatProducer catProducer;

    public CatService(CatProducer catProducer) {
        this.catProducer = catProducer;
    }

    public CatResponseList getAll(){
        return new RestTemplate()
                .getForObject(urlGetAll, CatResponseList.class);
    }

    public ResponseEntity<?> findById(Long id) {
        return new RestTemplate()
                .getForObject(urlGetById, ResponseEntity.class, id);
    }

    public  CatResponseList findAllByName(String name){
        return new RestTemplate()
                .getForObject(urlGetByName, CatResponseList.class, name);
    }

    public CatResponseList findAllByBread(String breed){
        return new RestTemplate()
                .getForObject(urlGetByBreed, CatResponseList.class, breed);
    }

    public CatResponseList findAllByColoring(Color color){
        return new RestTemplate()
                .getForObject(urlGetByColor, CatResponseList.class, color);
    }

    public void save(CatRequest cat){
         catProducer.sendCat(cat);
    }

    public void delete(Long id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlDelete, id);
    }
}
