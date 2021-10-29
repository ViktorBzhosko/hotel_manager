package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SearchController {

    private final ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/search/booking")
    public String findBookingRooms(@RequestBody BookingDto bookingDto, Model model) {
         searchService.findBookingRooms(bookingDto, model);
        return "rooms";
    }

    @GetMapping(value = "/search/empty")
    public String findEmptyRooms(@RequestBody BookingDto bookingDto, Model model) {
        searchService.findEmptyRooms(bookingDto, model);
        return "rooms";
    }

}
