package kirdin.lab.ownerMs.handlers.jsonHandlers;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.util.DtoUtil;
import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import kirdin.lab.ownerMs.services.OwnerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class PersistOwnerHandler {

    private final OwnerService ownerService;

    private final OwnerJsonProducer ownerJsonProducer;

    @Setter
    @Getter
    private OwnerRequest owner;

    public PersistOwnerHandler(OwnerService ownerService, OwnerJsonProducer ownerJsonProducer) {
        this.ownerService = ownerService;
        this.ownerJsonProducer = ownerJsonProducer;
    }

    public void handle() {
        ownerService.save(DtoUtil.castOwnerRequestToOwner(owner));
        ownerJsonProducer.sendStatus(HttpStatus.OK);
    }
}
