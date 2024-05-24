package kirdin.lab.ownerMs.handlers.massageHandlers;

import kirdin.lab.ownerMs.producers.OwnerJsonProducer;
import org.springframework.http.HttpStatus;

public class BadRequestHandler implements IMessageHandler {
    private final OwnerJsonProducer ownerJsonProducer;

    public BadRequestHandler(OwnerJsonProducer ownerJsonProducer) {
        this.ownerJsonProducer = ownerJsonProducer;
    }

    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        return null;
    }

    @Override
    public void handle(String key, Object value) {
        ownerJsonProducer.sendStatus(HttpStatus.BAD_REQUEST);
    }
}
