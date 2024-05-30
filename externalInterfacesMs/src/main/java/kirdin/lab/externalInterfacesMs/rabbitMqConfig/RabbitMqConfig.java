package kirdin.lab.externalInterfacesMs.rabbitMqConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Value("${rabbitmq.queue.json.ms.cat.name}")
    private String jsonToMsQueueCat;


    @Value("${rabbitmq.queue.json.ms.owner.name}")
    private String jsonToMsQueueOwner;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.ms.cat.key}")
    private String routingJsonToMsKeyCat;

    @Value("${rabbitmq.routing.json.ms.owner.key}")
    private String routingJsonToMsKeyOwner;

    @Bean
    public Queue jsonToMsQueueCat() {
        return new Queue(jsonToMsQueueCat);
    }

    @Bean
    public Queue jsonToMsQueueOwner() {
        return new Queue(jsonToMsQueueOwner);
    }


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }


    @Bean Binding jsonToMsBindingCat(){
        return BindingBuilder
                .bind(jsonToMsQueueCat())
                .to(exchange())
                .with(routingJsonToMsKeyCat);
    }

    @Bean Binding jsonToMsBindingOwner(){
        return BindingBuilder
                .bind(jsonToMsQueueOwner())
                .to(exchange())
                .with(routingJsonToMsKeyOwner);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }


}
