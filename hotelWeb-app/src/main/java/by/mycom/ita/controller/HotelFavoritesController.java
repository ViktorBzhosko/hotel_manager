package by.mycom.ita.controller;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HotelFavoritesController {

    private final IHotelFavoritesService hotelFavoritesService;

    @Autowired
    public HotelFavoritesController(IHotelFavoritesService hotelFavoritesService) {
        this.hotelFavoritesService = hotelFavoritesService;
    }


    @PostMapping("/favorite")
    public String createFavorite(@ModelAttribute HotelDto hotelDto, Model model) {
        hotelFavoritesService.favorites(hotelDto);
        return "redirect:/favorites/find";
    }

    @GetMapping("/favorites/find")
    public String findFavorite(Model model) {
        List<HotelFavoritesDto> favorites = hotelFavoritesService.showAllFavorites();
        model.addAttribute("favoritesHotels", favorites);
        return "favorites";
    }

    @ModelAttribute("HotelFavoritesDto")
    private HotelFavoritesDto createHotelFavoritesDto() {
        return new HotelFavoritesDto();
    }

    @ModelAttribute("HotelDto")
    private HotelDto createHotelDto() {
        return new HotelDto();
    }
}
