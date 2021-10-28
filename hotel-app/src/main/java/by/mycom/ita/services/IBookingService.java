package by.mycom.ita.services;

import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Booking;

public interface IBookingService {

    Booking create(Booking booking, Integer roomNumber, Long hotelId) throws DataNotFoundException;

    Booking updateByArrive(Long id) throws DataNotFoundException;

    Booking updateByLeave(Long id) throws DataNotFoundException;

    Booking updateByCanceled(Long id) throws DataNotFoundException;

    Booking readById(Long id);
}
