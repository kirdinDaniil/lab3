package kirdin.lab.catMs.handlers.massageHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;
import org.springframework.http.HttpStatus;

public class DeleteCatHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final CatService catService;
    private final CatJsonProducer catJsonProducer;

    public DeleteCatHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
        nextHandler = new BadRequestHandler(catJsonProducer);
    }

    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("deleteCat")){
            nextHandler.handle(key, value);
            return;
        }
        catService.delete((Long) value);
        catJsonProducer.sendStatus(HttpStatus.OK);
    }
}
