package by.mycom.ita.services.impl;

import by.mycom.ita.dao.BookingDao;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Room;
import by.mycom.ita.services.ISearchRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomSearchImpl implements ISearchRooms {

    private final BookingDao bookingDao;

    @Autowired
    public RoomSearchImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public List<Booking> findBookingRooms(Long hotelId, LocalDate dateChekIn, LocalDate dateChekOut) {
        return bookingDao.findBookingRooms(hotelId, dateChekIn, dateChekOut);
    }

    @Override
    public List<Room> findEmptyRooms(Long hotelId, LocalDate dateChekIn, LocalDate dateChekOut) {
        return bookingDao.findEmptyRooms(hotelId, dateChekIn, dateChekOut);
    }
}
