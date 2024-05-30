package kirdin.lab.ownerMs.handlers.jsonHandlers;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.util.DtoUtil;
import kirdin.lab.ownerMs.services.OwnerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class PersistOwnerHandler {

    private final OwnerService ownerService;

    @Setter
    @Getter
    private OwnerRequest owner;

    public PersistOwnerHandler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void handle() {
        ownerService.save(DtoUtil.castOwnerRequestToOwner(owner));
    }
}
