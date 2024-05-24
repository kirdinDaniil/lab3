package kirdin.lab.catMs.handlers.massageHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;
import org.springframework.http.HttpStatus;

public class BadRequestHandler implements IMessageHandler {
    private final CatJsonProducer catJsonProducer;

    public BadRequestHandler(CatJsonProducer catJsonProducer) {
        this.catJsonProducer = catJsonProducer;
    }

    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        return null;
    }

    @Override
    public void handle(String key, Object value) {
        catJsonProducer.sendStatus(HttpStatus.BAD_REQUEST);
    }
}
