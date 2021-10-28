package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import org.springframework.ui.Model;

public interface IHotelServices {

    void findHotels (Model model);

    void makeHotel (HotelDto hotelDto, Model model);

}
