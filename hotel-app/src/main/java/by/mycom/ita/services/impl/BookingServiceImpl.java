package by.mycom.ita.services.impl;

import by.mycom.ita.dao.BookingDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Booking;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.BookingStatus;
import by.mycom.ita.services.IBookingService;
import by.mycom.ita.services.ICommonUserService;
import by.mycom.ita.services.IEmailService;
import by.mycom.ita.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookingServiceImpl implements IBookingService {

    private final IHotelService iServiceHotel;
    private final ICommonUserService iServiceCommonUser;
    private final BookingDao bookingDao;
    private final IEmailService iEmailService;


    @Autowired
    public BookingServiceImpl(IHotelService iServiceHotel, ICommonUserService iServiceCommonUser, BookingDao bookingDao, IEmailService iEmailService) {
        this.iServiceHotel = iServiceHotel;
        this.iServiceCommonUser = iServiceCommonUser;
        this.bookingDao = bookingDao;
        this.iEmailService = iEmailService;

    }

    @Transactional
    @Override
    public Booking create(Booking booking, Long roomId, Long hotelId, Long userId) throws DataNotFoundException {
        CommonUser user = iServiceCommonUser.findById(userId);
        Hotel foundHotel = iServiceHotel.readById(hotelId);

        List<Room> freeRooms = bookingDao.findEmptyRooms(hotelId, booking.getDateChekIn(), booking.getDateChekOut());
        Room foundRoom = freeRooms.stream()
                .filter(room -> roomId.equals(room.getId()))
                .findFirst()
                .orElseThrow(DataNotFoundException::new);

        Booking createBooking = Booking.builder()
                .dateChekIn(booking.getDateChekIn())
                .dateChekOut(booking.getDateChekOut())
                .hotel(foundHotel)
                .room(foundRoom)
                .users(user)
                .bookingStatus(BookingStatus.RESERVED)
                .build();
        return bookingDao.save(createBooking);
    }

    private Booking update(Long id, BookingStatus status) throws DataNotFoundException {
        Booking foundedBooking = bookingDao.findById(id).orElseThrow(DataNotFoundException::new);
        foundedBooking.setBookingStatus(status);
        return bookingDao.save(foundedBooking);
    }

    @Override
    public Booking updateByArrive(Long id) throws DataNotFoundException {
        return update(id, BookingStatus.INUSE);
    }

    @Override
    public Booking updateByLeave(Long id) throws DataNotFoundException {
        return update(id, BookingStatus.PASSED);
    }

    @Override
    public Booking updateByCanceled(Long id) throws DataNotFoundException {
        Booking foundedBooking = bookingDao.findById(id).orElseThrow(DataNotFoundException::new);
        long userId = foundedBooking.getUsers().getId();
        iEmailService.sendSimpleMessage(userId);
        return update(id, BookingStatus.CANCELED);
    }

    @Override
    public List<Booking> findAll() {
        return bookingDao.findAll();
    }

    @Override
    public Booking readById(Long id) {
        return bookingDao.findById(id).orElseThrow(DataNotFoundException::new);
    }
}
