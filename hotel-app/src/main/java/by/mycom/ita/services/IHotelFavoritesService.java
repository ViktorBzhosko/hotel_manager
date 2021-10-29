package by.mycom.ita.services;

import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;

import java.util.List;

public interface IHotelFavoritesService {

    CommonUser favorites(Long userId, Hotel hotel);

    List<HotelFavorites> showAllFavorites();

    void deleteFavorite(Long id);

}
