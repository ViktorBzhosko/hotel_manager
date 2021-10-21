package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;
import by.mycom.ita.services.IHotelFavoritesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HotelFavoritesController {

    private final ObjectMapper objectMapper;
    private final IHotelFavoritesService favoritesService;

    public HotelFavoritesController(ObjectMapper objectMapper, IHotelFavoritesService favoritesService) {
        this.objectMapper = objectMapper;
        this.favoritesService = favoritesService;
    }

    @PostMapping("/create")
    public HotelFavoritesDto createFavorites(@RequestBody HotelDto hotelDto,
                                    @RequestBody CommonUserDto userDto) {
        final Hotel hotel = objectMapper.convertValue(hotelDto, Hotel.class);
        final CommonUser commonUser= objectMapper.convertValue(userDto, CommonUser.class);
        CommonUser favorites = favoritesService.favorites(commonUser,hotel);
        return objectMapper.convertValue(favorites, HotelFavoritesDto.class);
    }

    @GetMapping("/read/all")
    public List<HotelFavoritesDto> readAll() {
        List<HotelFavorites> list = favoritesService.showAllFavorites();
        return list.stream().map(favorites -> objectMapper.convertValue(favorites, HotelFavoritesDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        favoritesService.deleteFavorite(id);
    }
}
