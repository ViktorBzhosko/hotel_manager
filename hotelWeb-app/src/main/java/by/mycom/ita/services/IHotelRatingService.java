package by.mycom.ita.services;

import by.mycom.ita.dto.HotelRatingDto;
import org.springframework.ui.Model;

public interface IHotelRatingService {

    void estimateHotel(String id, HotelRatingDto hotelRatingDto, Model model);

}
