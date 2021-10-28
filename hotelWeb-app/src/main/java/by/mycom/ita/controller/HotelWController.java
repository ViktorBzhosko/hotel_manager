package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IHotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/showAllHotels")
    public String readAll(Model model) {
        hotelsService.findHotels(model);
        return "all-hotels";
    }

    @GetMapping("/hotel-create")
    public String createHotelForm(HotelDto hotelDto) {
        return "hotel-create";
    }

    @PostMapping("/hotel")
    public String createHotel(@ModelAttribute HotelDto hotelDto, Model model) {
        hotelsService.makeHotel(hotelDto, model);
        return "redirect:/hotel-create";
    }

    @ModelAttribute("hotel")
    private HotelDto createHotelDto() {
        return new HotelDto();
    }
}
