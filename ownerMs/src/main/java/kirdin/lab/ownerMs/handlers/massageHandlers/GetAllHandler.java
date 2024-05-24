package kirdin.lab.ownerMs.handlers.massageHandlers;

import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import kirdin.lab.ownerMs.services.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class GetAllHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final OwnerService ownerService;
    private final OwnerJsonProducer ownerJsonProducer;

    public GetAllHandler(OwnerService ownerService, OwnerJsonProducer ownerJsonProducer) {
        this.ownerService = ownerService;
        this.ownerJsonProducer = ownerJsonProducer;
        nextHandler = new GetByNameHandler(ownerService, ownerJsonProducer);
    }


    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("getAll")){
            nextHandler.handle(key, value);
            return;
        }
        ownerJsonProducer.sendOwners(ownerService.getAll());
    }
}
