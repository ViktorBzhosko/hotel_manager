package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import org.springframework.ui.Model;

import java.util.List;

public interface IHotelServices {

    List<HotelDto> findHotels();

    HotelDto makeHotel(HotelDto hotelDto);

    HotelDto updateTarget(Long id);

    void updatedHotel(HotelDto hotelDto);

    HotelDto findById (Long Id);

    void deleteHotel(String id);

}
