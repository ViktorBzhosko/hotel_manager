package by.mycom.ita.services;

import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Booking;

import java.util.List;

public interface IBookingService {

    Booking create(Booking booking, Long roomId, Long hotelId, Long userId) throws DataNotFoundException;

    Booking updateByArrive(Long id) throws DataNotFoundException;

    Booking updateByLeave(Long id) throws DataNotFoundException;

    Booking updateByCanceled(Long id) throws DataNotFoundException;

    List<Booking> findAll();

    Booking readById(Long id);
}
