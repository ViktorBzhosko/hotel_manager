package by.mycom.ita.controller;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.HotelRatingDto;
import by.mycom.ita.services.IHotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelEstimateController {

    private final IHotelRatingService hotelRatingService;

    @Autowired
    public HotelEstimateController(IHotelRatingService hotelRatingService) {
        this.hotelRatingService = hotelRatingService;
    }

    @GetMapping("/estimateForm")
    public String estimateForm(Model model) {
        model.addAttribute("estimate", new HotelRatingDto());
        return "all-hotels";
    }

    @PostMapping(value = "/estimate")
    private String estimateHotel(@RequestParam String hotelId,
                                 @ModelAttribute HotelRatingDto hotelRatingDto, Model model) {
        HotelRatingDto estimate = hotelRatingService.estimateHotel(hotelId, hotelRatingDto, model);
        model.addAttribute("estimate", estimate);
        return "redirect:/main";
    }

    @ModelAttribute("HotelRatingDto")
    private HotelRatingDto createHotelRatingDto() {
        return new HotelRatingDto();
    }
}
