package kirdin.lab.catMs.handlers.massageHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;

public class GetByNameHandler implements IMessageHandler {
    private IMessageHandler nextHandler;
    private final CatService catService;
    private final CatJsonProducer catJsonProducer;

    public GetByNameHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
        nextHandler = new GetByIdHandler(catService, catJsonProducer);
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
        catJsonProducer.sendCats(catService.findAllByName((String) value));
    }
}
