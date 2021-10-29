package by.mycom.ita.services;

import by.mycom.ita.dto.BookingDto;
import org.springframework.ui.Model;

public interface IBookingService {

    void createBooking(Long hotelId, Long roomId, BookingDto bookingDto, Model model);

    void updateForm(String id, Model model);

    void updateByArrive(BookingDto bookingDto, Model model);

    void updateByLeave(BookingDto bookingDto, Model model);

    void updateByCancelled (BookingDto bookingDto, Model model);

}
