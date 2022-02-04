package com.example.customerservice;


import com.example.customerservice.events.CreatedDocumentEvent;
import com.example.customerservice.events.DeletedDocumentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import com.mongodb.client.MongoClient;
import com.rabbitmq.client.Channel;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

//http://localhost:8080/producer?exchangeName=documentExchange&routingKey=queue.createDocument&messageData=DocumentWasCreated
//https://reflectoring.io/event-messaging-with-spring-boot-and-rabbitmq/

@Configuration
public class RabbitConfiguration {

    // The Event store `EmbeddedEventStore` delegates actual storage and retrieval of events to an `EventStorageEngine`.
    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    // The `MongoEventStorageEngine` stores each event in a separate MongoDB document
    //
    //
    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR353Module());

        return objectMapper;
    }


    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable("documentCreatedQueue").build();
    }

    @Value("${axon.amqp.exchange}")
    private String exchange;

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(exchange).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("queue.createDocument").noargs();
    }

    //TO DO
    //https://stackoverflow.com/questions/50836979/publishing-different-types-of-events-to-different-queues

    /**
     *

     By default, Axon Framework uses the package name of the event as the AMQP Routing Key. This means you can bind queues to topic exchanges using patterns to match against these routing keys. See https://www.rabbitmq.com/tutorials/tutorial-five-java.html for more information.

     You can customize Axon's behavior, by providing a custom RoutingKeyResolver (a simple Function that returns a String for a given EventMessage). This is then configured in the AMQPMessageConverter, which is responsible for creating an AMQP Message based on an Axon EventMessage (and vice versa). You can use the DefaultAMQPMessageConverter if you're fine with the default AMQP Message format.
     */
    @Bean
    public RoutingKeyResolver routingKeyResolver() {
        return new RoutingKeyResolver() {
            @Override
            public String resolveRoutingKey(EventMessage<?> eventMessage) {
                if(eventMessage.getPayload() instanceof CreatedDocumentEvent) {
                    System.out.println("CreatedDocument Routing");
                    return "document.event.created";
                }
                if(eventMessage.getPayload() instanceof DeletedDocumentEvent) {
                    System.out.println("DeletedDocument Routing");
                    return "document.event.deleted";
                }
                return "document.event";
            }
        };
    }

    @Bean
    public SpringAMQPMessageSource myQueueMessageSource(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = "documentCreatedQueue")
            @Override
            public void onMessage(Message message, Channel channel)  {
                System.out.println("<Received>: " + message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    //@Autowired
    //private AMQPProperties amqpProperties;

    //@Autowired
    //private RoutingKeyResolver routingKeyResolver;

    //@Autowired
    //private AMQPMessageConverter aMQPMessageConverter;

    //https://docs.axoniq.io/reference-guide/v/3.0/part-iii-infrastructure-components/event-processing
/**
    @Bean(initMethod = "start", destroyMethod = "shutDown")
    public SpringAMQPPublisher myPublisher(EventBus eventBus,
                                           ConnectionFactory connectionFactory, AMQPMessageConverter messageConverter) {
        SpringAMQPPublisher publisher = new SpringAMQPPublisher(eventBus);
        publisher.setExchangeName(exchange);
        publisher.setConnectionFactory(connectionFactory);
        publisher.setMessageConverter(messageConverter);
        publisher.setRoutingKeyResolver(routingKeyResolver());
        return publisher;
    }
**/

//TO DO: Tendenziell uerblussig, allerdings scheinen dasdurch irgendwelche Informationen resetet zu werden
    @Autowired
    public void config(AmqpAdmin admin, EventProcessingConfigurer processingConfigurer) {
        admin.declareExchange(exchange());
        //admin.declareQueue(queue());
        //admin.declareBinding(binding());
        //processingConfigurer.usingSubscribingEventProcessors();
    }


}
