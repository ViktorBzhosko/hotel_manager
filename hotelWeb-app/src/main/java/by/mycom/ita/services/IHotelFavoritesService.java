package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import org.springframework.ui.Model;

public interface IHotelFavoritesService {

    void favorites( HotelDto hotel, Model model);

    void showAllFavorites(Model model);

}
