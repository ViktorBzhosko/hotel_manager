package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FilterController {

    private final IFilterService filterService;

    @Autowired
    public FilterController(IFilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping(value = "/coincidences")
    public String coincidencesFilter(@ModelAttribute HotelDto hotelDto, Model model) {
        List<HotelDto> hotels = filterService.coincidences(hotelDto);
        model.addAttribute("hotels", hotels);
        return "filter";
    }

    @PostMapping(value = "/exact")
    public String exactFilter(@ModelAttribute HotelDto hotelDto, Model model) {
        List<HotelDto> hotels = filterService.exact(hotelDto);
        model.addAttribute("hotels", hotels);
        return "filterHotel";
    }

    @ModelAttribute("filter")
    private HotelDto createHotelDto() {
        return new HotelDto();
    }
}
