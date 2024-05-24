package kirdin.lab.ownerMs.producers;

import kirdin.lab.models.dto.OwnerResponse;
import kirdin.lab.models.jpa.Owner;
import kirdin.lab.models.util.DtoUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    public OwnerJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOwner(Owner owner){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, new OwnerResponse(owner));
    }

    public void sendOwners(List<Owner> owners){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, DtoUtil.castOwners(owners));
    }

    public void sendStatus(HttpStatus status){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, status);
    }

}
