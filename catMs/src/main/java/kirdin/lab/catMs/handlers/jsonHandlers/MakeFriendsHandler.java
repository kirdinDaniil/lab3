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
public class MakeFriendsHandler implements IJsonHandler {

    private final CatService catService;

    private final CatJsonProducer catJsonProducer;

    public MakeFriendsHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
    }

    @Getter
    @Setter
    private CatRequest firstCat;

    @Getter
    @Setter
    private CatRequest secondCat;

    @Override
    public void handle() {
        catService.makeFriends(DtoUtil.castCatRequestToCat(firstCat), DtoUtil.castCatRequestToCat(secondCat));
        catJsonProducer.sendStatus(HttpStatus.OK);
    }
}
