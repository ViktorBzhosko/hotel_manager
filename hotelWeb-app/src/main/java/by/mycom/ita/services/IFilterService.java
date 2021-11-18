package by.mycom.ita.services;

import by.mycom.ita.dto.HotelDto;
import org.springframework.ui.Model;

import java.util.List;

public interface IFilterService {

    List<HotelDto> coincidences(HotelDto hotelDto);

    List<HotelDto> exact(HotelDto hotelDto);

}
