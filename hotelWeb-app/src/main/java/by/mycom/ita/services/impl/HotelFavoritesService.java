package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.services.IAuthentication;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelFavoritesService implements IHotelFavoritesService {

    private final IAuthentication authentication;

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:8003/hotel-app/favorites";

    @Autowired
    public HotelFavoritesService(IAuthentication authentication, RestTemplate restTemplate) {
        this.authentication = authentication;
        this.restTemplate = restTemplate;
    }

    @Override
    public HotelDto favorites(HotelDto hotelDto) {
        Long userId = authentication.getCurrentUserId();
        return restTemplate.postForObject(Url + "/create/" + userId, hotelDto, HotelDto.class);

    }

    @Override
    public List<HotelFavoritesDto> showAllFavorites() {
        Long userId = authentication.getCurrentUserId();
        return Arrays.stream(restTemplate.getForObject(Url + "/read/all/" + userId, HotelFavoritesDto[].class))
                .collect(Collectors.toList());
    }
}
