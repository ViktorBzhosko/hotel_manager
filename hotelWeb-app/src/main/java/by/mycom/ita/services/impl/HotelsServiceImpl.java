package by.mycom.ita.services.impl;

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

    private final String Url = "http://hotel-app:8003/hotel-app";

    @Autowired
    public HotelsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<HotelDto> findHotels() {
        Weather[] values = Weather.values();
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(Url + "/hotel/read/all",
                        HotelDto[].class)))
                .peek(hotelDto -> hotelDto.setWeather(Weather.values()[new Random().nextInt(values.length)]))
                .collect(Collectors.toList());
    }

    @Override
    public HotelDto makeHotel(HotelDto hotelDto) {
        return restTemplate.postForObject(Url + "/hotel/create", hotelDto, HotelDto.class);
    }

    @Override
    public void updatedHotel(Long id, HotelDto hotelDto) {
        restTemplate.put(Url + "/hotel/update/" + id, hotelDto, HotelDto.class);
    }

    @Override
    public HotelDto findById(Long id) {
        return restTemplate.getForObject(Url + "/hotel/read/" + id, HotelDto.class);
    }

    @Override
    public void deleteHotel(String id) {
        restTemplate.delete(Url + "/hotel/delete/" + id);
    }
}
