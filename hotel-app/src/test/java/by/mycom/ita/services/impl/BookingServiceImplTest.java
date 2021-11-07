package by.mycom.ita.services.impl;

import by.mycom.ita.dao.BookingDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.BookingStatus;
import by.mycom.ita.model.enums.Comfort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private HotelServiceImpl hotelService;

    @Mock
    private CommonUserServiceImpl userService;

    @Mock
    private BookingDao bookingDao;

    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void whenCreate_thenOk() {

        CommonUser currentUser = getCurrentUser();
        Room room = createRoom();
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking expectedBooking = createBooking(simpleHotel, room, currentUser);

        Mockito.when(userService.findById(1L)).thenReturn(currentUser);
        Mockito.when(hotelService.readById(Mockito.any())).thenReturn(simpleHotel);
        Mockito.when(bookingDao.save(Mockito.any())).thenReturn(expectedBooking);

        Booking actualBooking = bookingService.create(expectedBooking, 1L, 1L,1L);
        Assertions.assertEquals(expectedBooking, actualBooking);
    }

    @Test
    void whenCreate_thenException() {
        CommonUser currentUser = getCurrentUser();
        Room room = createRoom();
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking expectedBooking = createBooking(simpleHotel, room, currentUser);

        Mockito.when(userService.findById(2L)).thenReturn(currentUser);
        Mockito.when(hotelService.readById(Mockito.any())).thenReturn(simpleHotel);

        Assertions.assertThrows(DataNotFoundException.class, () -> bookingService.create(expectedBooking, 2L, 2L,2L));
    }

    @Test
    void updateByArrive() {
        CommonUser currentUser = getCurrentUser();
        Room room = createRoom();
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking expectedBooking = createBooking(simpleHotel, room, currentUser);
        expectedBooking.setBookingStatus(BookingStatus.INUSE);
        Mockito.when(bookingDao.findById(1L)).thenReturn(Optional.of(expectedBooking));
        Mockito.when(bookingDao.save(Mockito.any())).thenReturn(expectedBooking);
        Booking actualBooking = bookingService.updateByArrive(1L);
        Assertions.assertEquals(expectedBooking.getBookingStatus(), actualBooking.getBookingStatus());
    }

    @Test
    void updateByLeave() {
        CommonUser currentUser = getCurrentUser();
        Room room = createRoom();
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking expectedBooking = createBooking(simpleHotel, room, currentUser);
        expectedBooking.setBookingStatus(BookingStatus.PASSED);
        Mockito.when(bookingDao.findById(1L)).thenReturn(Optional.of(expectedBooking));
        Mockito.when(bookingDao.save(Mockito.any())).thenReturn(expectedBooking);
        Booking actualBooking = bookingService.updateByLeave(1L);
        Assertions.assertEquals(expectedBooking.getBookingStatus(), actualBooking.getBookingStatus());
    }

    @Test
    void updateByCanceled() {
        CommonUser currentUser = getCurrentUser();
        Room room = createRoom();
        Hotel simpleHotel = createSimpleHotel(List.of(room));
        Booking expectedBooking = createBooking(simpleHotel, room, currentUser);
        expectedBooking.setBookingStatus(BookingStatus.CANCELED);

        Mockito.when(bookingDao.findById(1L)).thenReturn(Optional.of(expectedBooking));
        Mockito.when(bookingDao.save(Mockito.any())).thenReturn(expectedBooking);

        emailService.sendSimpleMessage(1L);
        Booking actualBooking = bookingService.updateByCanceled(1L);
        Assertions.assertEquals(expectedBooking.getBookingStatus(), actualBooking.getBookingStatus());
    }

    private Room createRoom() {
        return Room.builder()
                .id(1L)
                .numberOfRoom(1)
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
                .id(1L)
                .name("Mercuri")
                .location("Egypt")
                .rooms(rooms)
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }
}