package by.mycom.ita.services.impl;

import by.mycom.ita.dao.BookingDao;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoomSearchImplTest {

    @Autowired
    private BookingDao bookingDao;

    @Test
    void whenFindBookingRooms_thenReturnBookingRooms() {
        Booking booking = getBooking();
        Booking bookingRooms = bookingDao.findBookingRooms(1L, LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-05")).get(0);
        long expectedRoomId = booking.getRoom().getId();
        long actualRoomId = bookingRooms.getRoom().getId();
        Assertions.assertEquals(expectedRoomId, actualRoomId);
    }

    @Test
    void findEmptyRooms() {
        Room freeRooms = bookingDao.findEmptyRooms(1L, LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-05")).get(0);
        Assertions.assertEquals(2L, freeRooms.getId());
    }

    private Hotel createSimpleHotel() {
        return Hotel.builder()
                .id(1L)
                .build();
    }

    private Booking getBooking() {
        return Booking.builder()
                .hotel(createSimpleHotel())
                .dateChekIn(LocalDate.parse("2021-01-01"))
                .dateChekOut(LocalDate.parse("2021-01-05"))
                .room(createRoom())
                .build();
    }

    private Room createRoom(){
        return Room.builder()
                .id(1L)
                .build();
    }

}