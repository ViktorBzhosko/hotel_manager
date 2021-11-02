package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Room;
import by.mycom.ita.services.ISearchRooms;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ObjectMapper objectMapper;
    private final ISearchRooms searchRooms;

    @Autowired
    public SearchController(ObjectMapper objectMapper, ISearchRooms searchRooms) {
        this.objectMapper = objectMapper;
        this.searchRooms = searchRooms;
    }

    @ApiOperation(value = "Find bookings rooms for hotel ")
    @PostMapping("/booking")
    public List<BookingDto> findBookingRooms(@RequestBody BookingDto bookingDto) {
        final Booking booking = objectMapper.convertValue(bookingDto, Booking.class);
        List<Booking> bookingRooms = searchRooms.findBookingRooms(booking.getHotel().getId(), booking.getDateChekIn(), booking.getDateChekOut());
        return bookingRooms.stream().map(bookRooms -> objectMapper.convertValue(bookRooms, BookingDto.class))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Find free rooms for hotel ")
    @PostMapping("/empty")
    public List<RoomDto> findEmptyRooms(@RequestBody BookingDto bookingDto) {
        final Booking booking = objectMapper.convertValue(bookingDto, Booking.class);
        List<Room> bookingRooms = searchRooms.findEmptyRooms(booking.getHotel().getId(), booking.getDateChekIn(), booking.getDateChekOut());
        return bookingRooms.stream().map(nonBookRooms -> objectMapper.convertValue(nonBookRooms, RoomDto.class))
                .collect(Collectors.toList());
    }
}
