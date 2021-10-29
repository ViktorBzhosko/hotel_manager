package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HotelFavoritesController {

    private final IHotelFavoritesService hotelFavoritesService;

    @Autowired
    public HotelFavoritesController(IHotelFavoritesService hotelFavoritesService) {
        this.hotelFavoritesService = hotelFavoritesService;
    }

    @PostMapping("/favorite")
    public String createFavorite(@ModelAttribute HotelDto hotelDto, Model model) {
        hotelFavoritesService.favorites(hotelDto, model);
        return "all-hotels";
    }

    @GetMapping("/favorites/find")
    public String findFavorite(Model model) {
        hotelFavoritesService.showAllFavorites(model);
        return "favorites";
    }

    @ModelAttribute("favorites")
    private HotelFavoritesDto createHotelFavoritesDto(){
        return new HotelFavoritesDto();
    }
}
