package kirdin.lab.ownerMs.handlers.massageHandlers;

import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import kirdin.lab.ownerMs.services.OwnerService;

public class GetByIdHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final OwnerService ownerService;
    private final OwnerJsonProducer ownerJsonProducer;

    public GetByIdHandler(OwnerService ownerService, OwnerJsonProducer ownerJsonProducer) {
        this.ownerService = ownerService;
        this.ownerJsonProducer = ownerJsonProducer;
        nextHandler = new DeleteOwnerHandler(ownerService, ownerJsonProducer);
    }


    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("getById")){
            nextHandler.handle(key, value);
            return;
        }
        ownerJsonProducer.sendOwner(ownerService.findById((Long) value).get());
    }
}
