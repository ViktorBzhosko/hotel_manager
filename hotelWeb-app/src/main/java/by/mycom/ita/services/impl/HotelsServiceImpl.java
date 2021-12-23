package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.dto.enums.Weather;
import by.mycom.ita.services.IHotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HotelsServiceImpl implements IHotelServices {

    private final RestTemplate restTemplate;
    private final ConfigClient client;

    @Autowired
    public HotelsServiceImpl(RestTemplate restTemplate, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    public List<HotelDto> findHotels() {
        Weather[] values = Weather.values();
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject( client.serviceInfo()+"/hotel/read/all",
                        HotelDto[].class)))
                .peek(hotelDto -> hotelDto.setWeather(Weather.values()[new Random().nextInt(values.length)]))
                .collect(Collectors.toList());
    }

    @Override
    public HotelDto makeHotel(HotelDto hotelDto) {
        return restTemplate.postForObject( client.serviceInfo()+"/hotel/create", hotelDto, HotelDto.class);
    }

    @Override
    public void updatedHotel(Long id, HotelDto hotelDto) {
        restTemplate.put(client.serviceInfo()+"/hotel/update/" + id, hotelDto, HotelDto.class);
    }

    @Override
    public HotelDto findById(Long id) {
        return restTemplate.getForObject( client.serviceInfo()+"/hotel/read/" + id, HotelDto.class);
    }

    @Override
    public void deleteHotel(String id) {
        restTemplate.delete( client.serviceInfo()+"/hotel/delete/" + id);
    }
}
