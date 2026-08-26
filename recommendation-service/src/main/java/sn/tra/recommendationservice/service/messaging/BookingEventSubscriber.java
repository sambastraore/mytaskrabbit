package sn.tra.recommendationservice.service.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import sn.tra.recommendationservice.document.Recommendation;
import sn.tra.recommendationservice.repository.RecomRepository;
import sn.tra.recommendationservice.service.event.BookingCreatedEvent;

import java.util.ArrayList;

@Service
public class BookingEventSubscriber {
    private final PubSubTemplate pubSubTemplate;
    private final RecomRepository recomRepository;
    private final ObjectMapper objectMapper;

    public BookingEventSubscriber(PubSubTemplate pubSubTemplate, RecomRepository recomRepository, ObjectMapper objectMapper){
        this.pubSubTemplate = pubSubTemplate;
        this.recomRepository = recomRepository;
        this.objectMapper = objectMapper;
    }


    @PostConstruct
    public void start(){
        pubSubTemplate.subscribe(

                "recommendation-service-sub",
                message -> {
                    String payload = message.getPubsubMessage().getData().toStringUtf8();
                    System.out.println("Message reçu : " + payload);
                    try {
                        BookingCreatedEvent event = objectMapper.readValue(payload, BookingCreatedEvent.class);
                        Recommendation recommendation = recomRepository.findByUserId(event.getUserId()).orElseGet(() ->
                        {
                            Recommendation r = new Recommendation();
                            r.setUserId(event.getUserId());
                            r.setServiceIds(new ArrayList<>());
                            return r;

                        });
                        if (!recommendation.getServiceIds().contains(event.getServiceId())){
                            recommendation.getServiceIds().add(event.getServiceId());
                        }
                        recomRepository.save(recommendation);
                        message.ack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        );
    }
}
