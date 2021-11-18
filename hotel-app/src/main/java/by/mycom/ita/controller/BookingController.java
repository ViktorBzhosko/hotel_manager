package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final ObjectMapper objectMapper;
    private final IBookingService iServiceBooking;

    public BookingController(ObjectMapper objectMapper, IBookingService iServiceBooking) {
        this.objectMapper = objectMapper;
        this.iServiceBooking = iServiceBooking;
    }

    @ApiOperation(value = "Create booking rooms for user")
    @PostMapping("/create")
    public BookingDto create( @RequestBody BookingDto bookingDto) throws RuntimeException {
        final Booking booking = objectMapper.convertValue(bookingDto, Booking.class);
        Booking bookingCreated = iServiceBooking.create(booking, booking.getRoom().getId(), booking.getHotel().getId(),booking.getUsers().getId());
        return objectMapper.convertValue(bookingCreated, BookingDto.class);
    }

    @ApiOperation(value = "Update booking status by arrive")
    @PutMapping("/update/arrive/{booking_id}")
    public BookingDto updateByArrive(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByArrive(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @ApiOperation(value = "Update booking status by arrive")
    @PutMapping("/update/leave/{booking_id}")
    public BookingDto updateByLeave(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByLeave(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @ApiOperation(value = "Update booking status by arrive")
    @PutMapping("/update/canceled/{booking_id}")
    public BookingDto updateByCancelled(@PathVariable("booking_id") Long id) {
        final Booking bookingUpdated = iServiceBooking.updateByCanceled(id);
        return objectMapper.convertValue(bookingUpdated, BookingDto.class);
    }

    @ApiOperation(value = "Find all booking rooms")
    @GetMapping("/find/all")
    public List<BookingDto> readAll()  {
        List<Booking> list = iServiceBooking.findAll();
        return list.stream().map(booking -> objectMapper.convertValue(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Find booking by id")
    @GetMapping("/business/{id}")
    public BookingDto readById(@PathVariable Long id) {
        final Booking booking = iServiceBooking.readById(id);
        return objectMapper.convertValue(booking, BookingDto.class);
    }
}
