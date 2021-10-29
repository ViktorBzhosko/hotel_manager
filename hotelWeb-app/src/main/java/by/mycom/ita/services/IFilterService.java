package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import org.springframework.ui.Model;

public interface IFilterService {

    void coincidences(HotelDto hotelDto, Model model);

    void exact(HotelDto hotelDto, Model model);

}
