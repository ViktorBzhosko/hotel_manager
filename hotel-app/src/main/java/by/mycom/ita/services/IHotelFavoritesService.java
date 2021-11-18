package by.mycom.ita.services;

import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;

import java.util.List;

public interface IHotelFavoritesService {

    HotelFavorites favorites(Long userId, Hotel hotel);

    List<HotelFavorites> showAllFavorites(Long userId);

}
