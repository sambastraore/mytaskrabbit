package sn.tra.recommendationservice.service.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingCreatedEvent {
    private Long bookingId;
    private Long userId;
    private Long serviceId;
    private LocalDate bookingDate;

    public BookingCreatedEvent() {
    }
}
