package kirdin.lab.catMs.handlers.jsonHandlers;

import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.util.DtoUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class MakeFriendsHandler implements IJsonHandler {

    private final CatService catService;

    public MakeFriendsHandler(CatService catService) {
        this.catService = catService;
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
    }
}
