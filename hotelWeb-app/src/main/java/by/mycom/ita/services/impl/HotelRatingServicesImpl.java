package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.HotelRatingDto;
import by.mycom.ita.services.IHotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class HotelRatingServicesImpl implements IHotelRatingService {

    private final RestTemplate restTemplate;

    private final ConfigClient client;

    @Autowired
    public HotelRatingServicesImpl(RestTemplate restTemplate, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public HotelRatingDto estimateHotel(Long id, HotelRatingDto hotelRatingDto, Model model) {
//        String url = "http://localhost:8003/hotel-app";
        return restTemplate.postForObject(client.serviceInfo() + "/hotel/rating/" + id, hotelRatingDto, HotelRatingDto.class);
    }
}
