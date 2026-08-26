package sn.tra.bookingservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.tra.bookingservice.entity.Booking;
import sn.tra.bookingservice.service.BookingService;


@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService bookingService){
        this.service = bookingService;
    }

    @PostMapping
    public Booking create (@RequestBody Booking booking){
        return service.createBooking(booking);
    }
}