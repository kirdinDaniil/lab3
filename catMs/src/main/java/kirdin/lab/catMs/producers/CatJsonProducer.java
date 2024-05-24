package kirdin.lab.catMs.producers;

import kirdin.lab.models.dto.CatResponse;
import kirdin.lab.models.jpa.Cat;
import kirdin.lab.models.util.DtoUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

    public CatJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCat(Cat cat){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, new CatResponse(cat));
    }

    public void sendStatus(HttpStatus status){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, status);
    }

    public void sendCats(List<Cat> cats){
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, DtoUtil.castCats(cats));
    }
}
