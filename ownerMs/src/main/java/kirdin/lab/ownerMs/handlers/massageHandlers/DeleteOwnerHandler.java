package kirdin.lab.ownerMs.handlers.massageHandlers;

import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import kirdin.lab.ownerMs.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DeleteOwnerHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final OwnerService ownerService;
    private final OwnerJsonProducer ownerJsonProducer;

    public DeleteOwnerHandler(OwnerService ownerService, OwnerJsonProducer ownerJsonProducer) {
        this.ownerService = ownerService;
        this.ownerJsonProducer = ownerJsonProducer;
        nextHandler = new BadRequestHandler(ownerJsonProducer);
    }


    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("deleteOwner")){
            nextHandler.handle(key, value);
            return;
        }
        ownerService.delete((Long) value);
        ownerJsonProducer.sendStatus(HttpStatus.OK);
    }
}
