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

    @Value("${rabbitmq.queue.cat.name}")
    private String queueCat;

    @Value("${rabbitmq.queue.json.cat.name}")
    private String jsonQueueCat;

    @Value("${rabbitmq.queue.json.ms.cat.name}")
    private String jsonToMsQueueCat;

    @Value("${rabbitmq.queue.owner.name}")
    private String queueOwner;

    @Value("${rabbitmq.queue.json.owner.name}")
    private String jsonQueueOwner;

    @Value("${rabbitmq.queue.json.ms.owner.name}")
    private String jsonToMsQueueOwner;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.cat.key}")
    private String routingKeyCat;

    @Value("${rabbitmq.routing.json.cat.key}")
    private String routingJsonKeyCat;

    @Value("${rabbitmq.routing.json.ms.cat.key}")
    private String routingJsonToMsKeyCat;

    @Value("${rabbitmq.routing.owner.key}")
    private String routingKeyOwner;

    @Value("${rabbitmq.routing.json.owner.key}")
    private String routingJsonKeyOwner;

    @Value("${rabbitmq.routing.json.ms.owner.key}")
    private String routingJsonToMsKeyOwner;

    @Bean
    public Queue queueCat(){
        return new Queue(queueCat);
    }

    @Bean
    public Queue jsonQueueCat(){
        return new Queue(jsonQueueCat);
    }

    @Bean
    public Queue jsonToMsQueueCat() {
        return new Queue(jsonToMsQueueCat);
    }

    @Bean
    public Queue queueOwner(){
        return new Queue(queueOwner);
    }

    @Bean
    public Queue jsonQueueOwner(){
        return new Queue(jsonQueueOwner);
    }

    @Bean
    public Queue jsonToMsQueueOwner() {
        return new Queue(jsonToMsQueueOwner);
    }


    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingCat(){
        return BindingBuilder
                .bind(queueCat())
                .to(exchange())
                .with(routingKeyCat);
    }

    @Bean
    public Binding jsonBindingCat(){
        return BindingBuilder
                .bind(jsonQueueCat())
                .to(exchange())
                .with(routingJsonKeyCat);
    }

    @Bean Binding jsonToMsBindingCat(){
        return BindingBuilder
                .bind(jsonToMsQueueCat())
                .to(exchange())
                .with(routingJsonToMsKeyCat);
    }

    @Bean
    public Binding bindingOwner(){
        return BindingBuilder
                .bind(queueOwner())
                .to(exchange())
                .with(routingKeyOwner);
    }

    @Bean
    public Binding jsonBindingOwner(){
        return BindingBuilder
                .bind(jsonQueueOwner())
                .to(exchange())
                .with(routingJsonKeyOwner);
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
