package sn.tra.bookingservice.service;


import org.springframework.stereotype.Service;
import sn.tra.bookingservice.entity.Booking;
import sn.tra.bookingservice.event.BookingCreatedEvent;
import sn.tra.bookingservice.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository repository;
    private final BookingEventPublisher publisher;

    public BookingService(BookingRepository repository, BookingEventPublisher publisher){
        this.repository = repository;
        this.publisher = publisher;
    }

    public Booking createBooking(Booking booking){
        Booking saved = repository.save(booking);

        BookingCreatedEvent event = new BookingCreatedEvent(saved.getId(),saved.getUserId(), saved.getServiceId(), saved.getBookingDate());

        publisher.publish(event);

        return saved;

    }

}