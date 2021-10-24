package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelRatingDto;
import by.mycom.ita.model.HotelRating;
import by.mycom.ita.services.IHotelRatingService;
import by.mycom.ita.services.impl.HotelRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel/rating")
public class HotelRatingController {

    private final ObjectMapper objectMapper;
    private final IHotelRatingService hotelRatingService;

    public HotelRatingController(ObjectMapper objectMapper, HotelRatingService hotelRatingService) {
        this.objectMapper = objectMapper;
        this.hotelRatingService = hotelRatingService;
    }

    @PutMapping
    public void estimateHotel(@RequestParam(value = "id") Long id,
                              @RequestBody HotelRatingDto hotelRatingDto) {
        final HotelRating hotelRating = objectMapper.convertValue(hotelRatingDto, HotelRating.class);
        hotelRatingService.estimateHotel(id, hotelRating.getMark());
    }
}
