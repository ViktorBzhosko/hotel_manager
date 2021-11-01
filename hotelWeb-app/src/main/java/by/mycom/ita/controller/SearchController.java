package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SearchController {

    private final ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping(value = "/search/booking")
    public String findBookingRooms(@RequestBody BookingDto bookingDto, Model model) {
        List<BookingDto> rooms = searchService.findBookingRooms(bookingDto, model);
        model.addAttribute("bookings", rooms);
        return "booking-room";
    }

    @PostMapping(value = "/search/free")
    public String findEmptyRooms(@RequestBody BookingDto bookingDto, Model model) {
        List<BookingDto> emptyRooms = searchService.findEmptyRooms(bookingDto, model);
        model.addAttribute("bookings", emptyRooms);
        return "booking-room";
    }

    @ModelAttribute("BookingDto")
    private BookingDto createBookingDto() {
        return new BookingDto();
    }
}
