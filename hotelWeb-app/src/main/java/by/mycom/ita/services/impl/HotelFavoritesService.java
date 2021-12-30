package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.HotelFavoritesDto;
import by.mycom.ita.services.IAuthentication;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelFavoritesService implements IHotelFavoritesService {

    private final IAuthentication authentication;

    private final RestTemplate restTemplate;

    private final ConfigClient client;

    @Autowired
    public HotelFavoritesService(IAuthentication authentication, RestTemplate restTemplate, ConfigClient client) {
        this.authentication = authentication;
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public HotelDto favorites(HotelDto hotelDto) {
        Long userId = authentication.getCurrentUserId();
        return restTemplate.postForObject( client.serviceInfo()+"/favorites/create/" + userId, hotelDto, HotelDto.class);

    }

    @Override
    public List<HotelFavoritesDto> showAllFavorites() {
        Long userId = authentication.getCurrentUserId();
        return Arrays.stream(restTemplate.getForObject( client.serviceInfo()+"/favorites/read/all/" + userId, HotelFavoritesDto[].class))
                .collect(Collectors.toList());
    }
}
