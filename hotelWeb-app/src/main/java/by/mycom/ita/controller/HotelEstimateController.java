package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelRatingDto;
import by.mycom.ita.services.IHotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping(value = "/estimate")
    private String estimateHotel(@RequestParam String hotel_id,
                                 @ModelAttribute HotelRatingDto hotelRatingDto, Model model) {
        hotelRatingService.estimateHotel(hotel_id, hotelRatingDto, model);
        return "redirect:/main";
    }
}
