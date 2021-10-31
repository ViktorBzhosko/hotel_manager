package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelRatingDto;
import by.mycom.ita.services.IHotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class IHotelRatingServicesImpl implements IHotelRatingService {

    private final RestTemplate restTemplate;

    @Autowired
    public IHotelRatingServicesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HotelRatingDto estimateHotel(String id, HotelRatingDto hotelRatingDto, Model model) {
        String url = "http://localhost:8003/hotel-app";
        return restTemplate.postForObject(url + "/hotel/rating/" + id, hotelRatingDto, HotelRatingDto.class);
    }
}
