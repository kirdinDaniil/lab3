package kirdin.lab.externalInterfacesMs.producers;

import kirdin.lab.models.dto.CatRequest;
import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.jpa.Owner;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class CatProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.ms.cat.key}")
    private String routingJsonToMsKey;


    private final RabbitTemplate rabbitTemplate;

    public CatProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCat(CatRequest cat){
        rabbitTemplate.convertAndSend(exchange, routingJsonToMsKey, cat);
    }

    public void sendCats(Pair<CatRequest, CatRequest> cats){
        rabbitTemplate.convertAndSend(exchange, routingJsonToMsKey, cats);
    }
}
