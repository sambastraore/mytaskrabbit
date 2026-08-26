package sn.tra.bookingservice.event;

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


    public BookingCreatedEvent(Long bookingId, Long userId, Long serviceId, LocalDate bookingDate){
        this.bookingId = bookingId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.bookingDate = bookingDate;
    }
}
