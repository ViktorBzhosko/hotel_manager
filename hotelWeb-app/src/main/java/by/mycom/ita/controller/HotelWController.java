package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IHotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HotelWController {

    private final IHotelServices hotelsService;

    @Autowired
    public HotelWController(IHotelServices hotelsService) {
        this.hotelsService = hotelsService;
    }

    @GetMapping(value = "/")
    public String homePage() {
        return "main";
    }

    @GetMapping("/hotel-create")
    public String createHotelForm() {
        return "hotel-create";
    }

    @GetMapping(value = "/update")
    public String update(Model model) {
        return "update-form";
    }

    @PostMapping(value = "/updatedHotel")
    public String updated(@RequestParam Long id, @ModelAttribute HotelDto hotelDto, Model model) {
        model.addAttribute("hotel", hotelDto);
        hotelsService.updatedHotel(id, hotelDto);
        return "redirect:/hotels";
    }

    @GetMapping("/hotel")
    public String getPageCreateHotel() {
        return "hotel-create";
    }

    @PostMapping("/hotel")
    public String createHotel(@ModelAttribute HotelDto hotelDto, Model model) {
        HotelDto createHotel = hotelsService.makeHotel(hotelDto);
        model.addAttribute("Hotel", createHotel);
        return "redirect:/hotels";
    }

    @GetMapping("/hotels")
    public String readAll(Model model) {
        List<HotelDto> hotels = hotelsService.findHotels();
        model.addAttribute("hotels", hotels);
        return "all-hotels";
    }

    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam(value = "id", required = false) String id, Model model) {
        model.addAttribute("id", id);
        hotelsService.deleteHotel(id);
        return "redirect:/hotels";
    }

    @GetMapping(value = "/readById")
    public String readById(@RequestParam(value = "id", required = false) Long id, Model model) {
        HotelDto hotel = hotelsService.findById(id);
        model.addAttribute("laptops", hotel);
        return "all-hotels";
    }

    @ModelAttribute("HotelDto")
    private HotelDto createHotelDto() {
        return new HotelDto();
    }
}
