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
    public BookingDto create(@RequestParam Long id,
                             @RequestParam Integer number,
                             @RequestBody BookingDto bookingDto) throws RuntimeException {
        final Booking booking = objectMapper.convertValue(bookingDto, Booking.class);
        Booking bookingCreated = iServiceBooking.create(booking, number, id);
        return objectMapper.convertValue(bookingCreated, BookingDto.class);
    }

    @PutMapping("/update/arrive/{client_id}")
    public BookingDto updateByArrive(@PathVariable("client_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByArrive(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @PutMapping("/update/leave/{client_id}")
    public BookingDto updateByLeave(@PathVariable("client_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByLeave(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @PutMapping("/update/canceled/{client_id}")
    public BookingDto updateByCancelled(@PathVariable("client_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByCanceled(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }
}
