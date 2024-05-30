package kirdin.lab.catMs.handlers.jsonHandlers;

import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.util.DtoUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
public class PersistCatHandler implements IJsonHandler {

    private final CatService catService;

    @Setter
    @Getter
    private CatRequest cat;

    public PersistCatHandler(CatService catService) {
        this.catService = catService;
    }

    @Override
    public void handle() {
        catService.save(DtoUtil.castCatRequestToCat(cat));
    }
}
