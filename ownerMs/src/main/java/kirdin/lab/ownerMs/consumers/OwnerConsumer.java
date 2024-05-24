package kirdin.lab.ownerMs.consumers;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.ownerMs.handlers.jsonHandlers.PersistOwnerHandler;
import kirdin.lab.ownerMs.handlers.massageHandlers.GetAllHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class OwnerConsumer {
    public final GetAllHandler massageHandlerChain;

    public final PersistOwnerHandler jsonOwnerHandler;


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(Message message){
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        for (Map.Entry<String, Object> header : headers.entrySet()){
            massageHandlerChain.handle(header.getKey(), header.getValue());
        }
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.ms.name}"})
    public void consumeSingleCatJson(OwnerRequest ownerRequest){
        jsonOwnerHandler.setOwner(ownerRequest);
        jsonOwnerHandler.handle();
    }
}
