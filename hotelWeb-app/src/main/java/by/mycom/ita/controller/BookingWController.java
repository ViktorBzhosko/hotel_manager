package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingWController {

    private final IBookingService bookingService;

    @Autowired
    public BookingWController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public String createBooking( @ModelAttribute BookingDto bookingDto,  Model model){
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        model.addAttribute("Booking", createdBooking);
        return "redirect:/hotels";
    }

    @GetMapping(value = "/booking-updateForm")
    public String updateForm(@RequestParam(value = "id", required = false) String id, Model model) {
        BookingDto bookingDto = bookingService.updateForm(id, model);
        model.addAttribute("booking", bookingDto);
        return "booking-update";
    }

    @GetMapping(value = "/booking")
    public String getPageBooking() {
        return "booking-form";
    }

    @PostMapping(value = "/update/arrive")
    public String updateByArrive(@ModelAttribute BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        bookingService.updateByArrive(bookingDto);
        return "redirect:/allbooking";
    }

    @PostMapping(value = "/update/leave")
    public String updateByLeave(@ModelAttribute BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        bookingService.updateByLeave(bookingDto);
        return "redirect:/allbooking";
    }

    @PostMapping(value = "/update/cancel")
    public String updateByCanceled(@ModelAttribute BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        bookingService.updateByCancelled(bookingDto);
        return "redirect:/allbooking";
    }

    @GetMapping("/allbooking")
    public String readAll(Model model) {
        List<BookingDto> bookings = bookingService.findAllBooking();
        model.addAttribute("bookings", bookings);
        return "booking-form";
    }

    @ModelAttribute("BookingDto")
    private BookingDto createBookingDto() {
        return new BookingDto();
    }
}
