package kirdin.lab.catMs.handlers.jsonHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.util.DtoUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class PersistCatHandler implements IJsonHandler {

    private final CatService catService;

    private final CatJsonProducer catJsonProducer;

    @Setter
    @Getter
    private CatRequest cat;

    public PersistCatHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
    }

    @Override
    public void handle() {
        catService.save(DtoUtil.castCatRequestToCat(cat));
        catJsonProducer.sendStatus(HttpStatus.OK);
    }
}
