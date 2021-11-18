package by.mycom.ita.services;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.RoomDto;
import org.springframework.ui.Model;

import java.util.List;

public interface ISearchService {

    List<BookingDto> findBookingRooms(BookingDto booking, Model model);

    List<RoomDto> findEmptyRooms(BookingDto bookingDto, Model model);

}
