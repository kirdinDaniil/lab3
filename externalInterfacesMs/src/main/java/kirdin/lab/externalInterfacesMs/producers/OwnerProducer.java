package kirdin.lab.externalInterfacesMs.producers;

import kirdin.lab.models.dto.OwnerRequest;
import kirdin.lab.models.dto.OwnerResponse;
import kirdin.lab.models.jpa.Owner;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OwnerProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.owner.key}")
    private String routingJsonToMsKey;

    @Value("${rabbitmq.routing.json.ms.owner.key}")
    private String routingMassageKey;

    private final RabbitTemplate rabbitTemplate;

    public OwnerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOwner(OwnerRequest owner){
        rabbitTemplate.convertAndSend(exchange, routingJsonToMsKey, new OwnerRequest(owner.getName(), owner.getBirthdate()));
    }

    public void sendMassage(Message message){
        rabbitTemplate.send(exchange, routingMassageKey, message);
    }
}
