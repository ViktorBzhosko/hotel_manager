package by.mycom.ita.services;

import by.mycom.ita.model.Hotel;

public interface IHotelRatingService {

    Hotel estimateHotel(Long hotelId, Integer mark);

}
