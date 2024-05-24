package kirdin.lab.catMs.handlers.massageHandlers;

import kirdin.lab.catMs.producers.CatJsonProducer;
import kirdin.lab.catMs.services.CatService;
import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.jpa.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetByIdHandler implements IMessageHandler{
    private IMessageHandler nextHandler;
    private final CatService catService;
    private final CatJsonProducer catJsonProducer;

    public GetByIdHandler(CatService catService, CatJsonProducer catJsonProducer) {
        this.catService = catService;
        this.catJsonProducer = catJsonProducer;
        nextHandler = new GetByColorHandler(catService, catJsonProducer);
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
        List<Cat> cats = new ArrayList<>();
        cats.add(catService.findById((Long) value).get());
        catJsonProducer.sendCats(cats);
    }
}
