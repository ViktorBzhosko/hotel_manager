package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.model.Booking;
import by.mycom.ita.services.IBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final ObjectMapper objectMapper;
    private final IBookingService iServiceBooking;

    public BookingController(ObjectMapper objectMapper, IBookingService iServiceBooking) {
        this.objectMapper = objectMapper;
        this.iServiceBooking = iServiceBooking;
    }

    @PostMapping("/create")
    public BookingDto create(@RequestParam Long hotelId,
                             @RequestParam Long roomId,
                             @RequestParam Long userId,
                             @RequestBody BookingDto bookingDto) throws RuntimeException {
        final Booking booking = objectMapper.convertValue(bookingDto, Booking.class);
        Booking bookingCreated = iServiceBooking.create(booking, roomId, hotelId,userId);
        return objectMapper.convertValue(bookingCreated, BookingDto.class);
    }

    @PutMapping("/update/arrive/{booking_id}")
    public BookingDto updateByArrive(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByArrive(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @PutMapping("/update/leave/{booking_id}")
    public BookingDto updateByLeave(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByLeave(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @PutMapping("/update/canceled/{booking_id}")
    public BookingDto updateByCancelled(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByCanceled(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @GetMapping("/business/{id}")
    public BookingDto readById(@PathVariable Long id) {
        final Booking booking = iServiceBooking.readById(id);
        return objectMapper.convertValue(booking, BookingDto.class);
    }
}
