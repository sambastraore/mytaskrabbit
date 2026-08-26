package sn.tra.bookingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;
import sn.tra.bookingservice.event.BookingCreatedEvent;

@Service
public class BookingEventPublisher {

    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;


    public BookingEventPublisher(
            PubSubTemplate pubSubTemplate,
            ObjectMapper objectMapper
    ) {
        this.pubSubTemplate = pubSubTemplate;
        this.objectMapper = objectMapper;
    }


    public void publish(BookingCreatedEvent event) {

        try {

            String message = objectMapper.writeValueAsString(event);

            pubSubTemplate.publish(
                    "booking-events",
                    message
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}