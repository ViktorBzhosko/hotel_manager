package by.mycom.ita.dao;

import by.mycom.ita.model.Booking;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.BookingStatus;
import by.mycom.ita.model.enums.Comfort;
import by.mycom.ita.services.impl.BookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookingDaoTest {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    BookingServiceImpl bookingService;

    Integer expectedNumber = 1;
    Integer expectedEmptyNumber = 2;

    @Test
    void findBookingRooms() {
        CommonUser currentUser = getCurrentUser();
        Room room = createRoom(1);
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking newBooking = createBooking(simpleHotel, room, currentUser);
        bookingDao.save(newBooking);
        List<Booking> bookingRooms = bookingDao.findBookingRooms(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-10"));
        int numberOfRoom = bookingRooms.get(0).getRoom().getNumberOfRoom();
        Assertions.assertNotNull(bookingRooms);
        Assertions.assertEquals(expectedNumber, numberOfRoom);

    }

    @Test
    void findEmptyRooms() {
        CommonUser currentUser = getCurrentUser();
        Room room1 = createRoom(1);
        Room room2 = createRoom(2);
        Hotel simpleHotel = createSimpleHotel(List.of(room1, room2));
        Booking booking = createBooking(simpleHotel, room1, currentUser);
        bookingDao.save(booking);
        List<Booking> emptyRooms = bookingDao.findEmptyRooms(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-01-10"));
        int numberOfRoom = emptyRooms.get(1).getRoom().getNumberOfRoom();
        Assertions.assertNotNull(emptyRooms);
        Assertions.assertEquals(expectedEmptyNumber, numberOfRoom);


    }

    private Room createRoom(Integer number) {
        return Room.builder()
                .numberOfRoom(number)
                .comfort(Comfort.STANDARD)
                .accommodation(Accommodation.SINGLE)
                .build();
    }

    private Booking createBooking(Hotel hotel, Room room, CommonUser user) {
        return Booking.builder()
                .dateChekIn(LocalDate.parse("2021-01-01"))
                .dateChekOut(LocalDate.parse("2021-01-10"))
                .hotel(hotel)
                .room(room)
                .users(user)
                .bookingStatus(BookingStatus.RESERVED)
                .build();
    }

    private CommonUser getCurrentUser() {
        return CommonUser.builder()
                .firstName("Viktor")
                .secondName("Bzhosko")
                .passport("1562")
                .email("vic308@mail.ru")
                .phoneNumber(1489)
                .build();
    }

    private Hotel createSimpleHotel(List<Room> rooms) {
        return Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .rooms(rooms)
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }

    private Hotel createOtherHotel() {
        return Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }

    private Booking createOtherBooking() {
        return Booking.builder()
                .dateChekIn(LocalDate.parse("2021-01-01"))
                .dateChekOut(LocalDate.parse("2021-01-10"))
                .build();
    }
}