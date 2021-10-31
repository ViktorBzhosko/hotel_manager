package by.mycom.ita.services;

import by.mycom.ita.dto.BookingDto;
import org.springframework.ui.Model;

import java.util.List;

public interface ISearchService {

    List<BookingDto> findBookingRooms(BookingDto booking, Model model);

    List<BookingDto> findEmptyRooms(BookingDto bookingDto, Model model);

}
