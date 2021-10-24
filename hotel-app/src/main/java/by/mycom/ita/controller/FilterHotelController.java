package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filter")
public class FilterHotelController {

    private final ObjectMapper objectMapper;
    private final IHotelFilter hotelFilter;


    public FilterHotelController(ObjectMapper objectMapper, IHotelFilter hotelFilter) {
        this.objectMapper = objectMapper;
        this.hotelFilter = hotelFilter;
    }

    @PostMapping(value = "/coincidences")
    public List<HotelDto> coincidencesFiltering(@RequestBody HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        List<Hotel> hotels = hotelFilter.coincidencesFiltering(hotel);
        return hotels.stream()
                .map(convertHotel -> objectMapper.convertValue(convertHotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/exact")
    public List<HotelDto> exactFiltering(@RequestBody HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        List<Hotel> hotels = hotelFilter.exactFiltering(hotel);
        return hotels.stream()
                .map(convertHotel -> objectMapper.convertValue(convertHotel, HotelDto.class))
                .collect(Collectors.toList());
    }
}
