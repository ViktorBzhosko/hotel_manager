package by.mycom.ita.services;

import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelRating;

import java.util.List;

public interface IHotelRatingService {

    Hotel estimateHotel(Long hotelId, Integer mark);

    List<HotelRating> createDefaultRating();
}
