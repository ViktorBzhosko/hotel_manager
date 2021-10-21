package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelFilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filter")
public class FilterHotelController {

    private final ObjectMapper objectMapper;
    private final IHotelFilterService hotelFilterService;

    public FilterHotelController(ObjectMapper objectMapper, IHotelFilterService hotelFilterService) {
        this.objectMapper = objectMapper;
        this.hotelFilterService = hotelFilterService;
    }

    @PostMapping("/name")
    public List<HotelDto> filterByName(@RequestParam HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        List<Hotel> hotels = hotelFilterService.filterByName(hotel.getName());
        return hotels.stream()
                .map(convertHotel -> objectMapper.convertValue(convertHotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/location")
    public List<HotelDto> filterByLocation(@RequestParam HotelDto hotelDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        List<Hotel> hotels = hotelFilterService.filterByLocation(hotel.getLocation());
        return hotels.stream()
                .map(convertHotel -> objectMapper.convertValue(convertHotel, HotelDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/rating")
    public List<HotelDto> filterByRating() {
        List<Hotel> hotels = hotelFilterService.filterByRanking();
        return hotels.stream()
                .map(convertHotel -> objectMapper.convertValue(convertHotel, HotelDto.class))
                .collect(Collectors.toList());
    }
}
