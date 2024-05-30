package kirdin.lab.ownerMs.consumers;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.ownerMs.handlers.jsonHandlers.PersistOwnerHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class OwnerConsumer {

    public final PersistOwnerHandler jsonOwnerHandler;

    @RabbitListener(queues = {"${rabbitmq.queue.json.ms.name}"})
    public void consumeSingleCatJson(OwnerRequest ownerRequest){
        jsonOwnerHandler.setOwner(ownerRequest);
        jsonOwnerHandler.handle();
    }
}
