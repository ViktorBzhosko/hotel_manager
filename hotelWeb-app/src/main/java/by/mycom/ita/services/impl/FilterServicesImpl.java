package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FilterServicesImpl implements IFilterService {

    private final RestTemplate restTemplate;

    private final ConfigClient client;
//    private final String Url = "http://localhost:8003/hotel-app";

    @Autowired
    public FilterServicesImpl(RestTemplate restTemplate, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public List<HotelDto> coincidences(HotelDto hotelDto) {
        ResponseEntity<HotelDto[]> responseEntity =
                restTemplate.postForEntity(client.serviceInfo() + "/filter/coincidences", hotelDto, HotelDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<HotelDto> exact(HotelDto hotelDto) {
        ResponseEntity<HotelDto[]> responseEntity =
                restTemplate.postForEntity(client.serviceInfo() + "/filter/exact", hotelDto, HotelDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }
}
