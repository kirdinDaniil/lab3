package kirdin.lab.catMs.consumers;

import kirdin.lab.catMs.handlers.jsonHandlers.MakeFriendsHandler;
import kirdin.lab.catMs.handlers.jsonHandlers.PersistCatHandler;
import kirdin.lab.catMs.handlers.massageHandlers.GetAllHandler;
import kirdin.lab.models.dto.CatRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class CatConsumer {

    public final GetAllHandler massageHandlerChain;

    public final PersistCatHandler jsonSingleCatHandler;

    public final MakeFriendsHandler jsonPairCatsHandler;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(Message message){
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        for (Map.Entry<String, Object> header : headers.entrySet()){
            massageHandlerChain.handle(header.getKey(), header.getValue());
        }
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.ms.name}"})
    public void consumeSingleCatJson(CatRequest catRequest){
        jsonSingleCatHandler.setCat(catRequest);
        jsonSingleCatHandler.handle();
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.ms.name}"})
    public void consumePairCatsJson(Pair<CatRequest, CatRequest> catRequest){
        jsonPairCatsHandler.setFirstCat(catRequest.getFirst());
        jsonPairCatsHandler.setSecondCat(catRequest.getSecond());
        jsonPairCatsHandler.handle();
    }
}
