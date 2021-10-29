package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FilterServicesImpl implements IFilterService {

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:5438/testdb";

    @Autowired
    public FilterServicesImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void coincidences(HotelDto hotelDto, Model model) {
        ResponseEntity<HotelDto[]> responseEntity =
                restTemplate.postForEntity(Url + "/filter/coincidences", hotelDto, HotelDto[].class);
        List<HotelDto> hotels = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        model.addAttribute("hotels", hotels);
    }

    @Override
    public void exact(HotelDto hotelDto, Model model) {
        ResponseEntity<HotelDto[]> responseEntity =
                restTemplate.postForEntity(Url + "/filter/exact", hotelDto, HotelDto[].class);
        List<HotelDto> hotels = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        model.addAttribute("hotels", hotels);
    }
}
