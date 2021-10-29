package by.mycom.ita.services;

import by.mycom.ita.dto.BookingDto;
import org.springframework.ui.Model;

public interface ISearchService {

    void findBookingRooms(BookingDto booking, Model model);

    void findEmptyRooms(BookingDto bookingDto, Model model);

}
