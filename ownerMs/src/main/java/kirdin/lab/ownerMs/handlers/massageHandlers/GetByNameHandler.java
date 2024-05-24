package kirdin.lab.ownerMs.handlers.massageHandlers;

import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import kirdin.lab.ownerMs.services.OwnerService;

public class GetByNameHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final OwnerService ownerService;
    private final OwnerJsonProducer ownerJsonProducer;

    public GetByNameHandler(OwnerService ownerService, OwnerJsonProducer ownerJsonProducer) {
        this.ownerService = ownerService;
        this.ownerJsonProducer = ownerJsonProducer;
        nextHandler = new GetByIdHandler(ownerService, ownerJsonProducer);
    }


    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("getByName")){
            nextHandler.handle(key, value);
            return;
        }
        ownerJsonProducer.sendOwners(ownerService.findByName((String) value));
    }
}
