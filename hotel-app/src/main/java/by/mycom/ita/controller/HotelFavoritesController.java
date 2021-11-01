package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;
import by.mycom.ita.services.IHotelFavoritesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
public class HotelFavoritesController {

    private final ObjectMapper objectMapper;
    private final IHotelFavoritesService favoritesService;

    @Autowired
    public HotelFavoritesController(ObjectMapper objectMapper, IHotelFavoritesService favoritesService) {
        this.objectMapper = objectMapper;
        this.favoritesService = favoritesService;
    }

    @PostMapping("/create/{userId}")
    public HotelFavoritesDto createFavorites(@RequestBody HotelDto hotelDto,
                                             @PathVariable Long userId) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        CommonUser favorites = favoritesService.favorites(userId, hotel);
        return objectMapper.convertValue(favorites, HotelFavoritesDto.class);
    }

    @GetMapping("/read/all/{userId}")
    public List<HotelFavoritesDto> readAll(@PathVariable Long userId) {
        List<HotelFavorites> userFavorites = favoritesService.showAllFavorites(userId);
        return userFavorites.stream().map(favorites -> objectMapper.convertValue(favorites, HotelFavoritesDto.class))
                .collect(Collectors.toList());
    }

}
