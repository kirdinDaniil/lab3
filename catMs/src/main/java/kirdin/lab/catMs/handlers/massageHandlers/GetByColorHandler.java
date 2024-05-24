package kirdin.lab.catMs.handlers.massageHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.jpa.Color;

public class GetByColorHandler implements IMessageHandler{
    private IMessageHandler nextHandler;
    private final CatService catService;
    private final CatJsonProducer catJsonProducer;

    public GetByColorHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
        nextHandler = new GetByBreedHandler(catService, catJsonProducer);
    }

    @Override
    public IMessageHandler setNext(IMessageHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    @Override
    public void handle(String key, Object value) {
        if (!key.equals("getByColor")){
            nextHandler.handle(key, value);
            return;
        }
        catJsonProducer.sendCats(catService.findAllByColoring((Color)value));
    }
}
