package com.example.axonkafkatest;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericDomainEventMessage;
import org.axonframework.extensions.kafka.eventhandling.producer.KafkaPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
class KafkaEventPublisherTests {

    @Autowired
    private EventBus eventBus;

    @MockBean
    private KafkaPublisher publisher;

    @Test
    void shouldPublishToKafka() throws Exception {

        GenericDomainEventMessage message = new GenericDomainEventMessage("type", "id", 0L, "payload");
        eventBus.publish(message);

        verify(publisher).send(message);
    }
}
