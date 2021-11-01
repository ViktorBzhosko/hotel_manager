package by.mycom.ita.services;

import by.mycom.ita.dto.BookingDto;
import org.springframework.ui.Model;

import java.util.List;

public interface IBookingService {

    BookingDto createBooking(BookingDto bookingDto);

    BookingDto updateForm(String id, Model model);

    void updateByArrive(BookingDto bookingDto);

    void updateByLeave(BookingDto bookingDto);

    void updateByCancelled (BookingDto bookingDto);

    List<BookingDto> findAllBooking();

}
