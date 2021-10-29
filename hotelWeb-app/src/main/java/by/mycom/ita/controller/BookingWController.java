package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingWController {

    private final IBookingService bookingService;

    @Autowired
    public BookingWController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/booking-updateForm")
    public String updateForm(@RequestParam(value = "id", required = false) String id, Model model) {
        bookingService.updateForm(id, model);
        return "booking-update";
    }

    @PostMapping(value = "/update/arrive")
    public String updateByArrive(@ModelAttribute BookingDto bookingDto, Model model) {
        bookingService.updateByArrive(bookingDto, model);
        return "booking-update";
    }

    @PostMapping(value = "/update/leave")
    public String updateByLeave(@ModelAttribute BookingDto bookingDto, Model model) {
        bookingService.updateByLeave(bookingDto, model);
        return "booking-update";
    }

    @PostMapping(value = "/update/cancel")
    public String updateByCanceled(@ModelAttribute BookingDto bookingDto, Model model) {
        bookingService.updateByCancelled(bookingDto, model);
        return "booking-update";
    }

    @ModelAttribute("booking")
    private BookingDto createBookingDto() {
        return new BookingDto();
    }
}
