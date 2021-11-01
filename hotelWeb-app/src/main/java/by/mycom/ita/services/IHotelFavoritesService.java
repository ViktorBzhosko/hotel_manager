package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import org.springframework.ui.Model;

import java.util.List;

public interface IHotelFavoritesService {

    HotelDto favorites(HotelDto hotel);

    List<HotelFavoritesDto> showAllFavorites();

}
