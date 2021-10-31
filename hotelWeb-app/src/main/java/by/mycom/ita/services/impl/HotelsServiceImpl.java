package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IHotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HotelsServiceImpl implements IHotelServices {

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:8003/hotel-app";

    @Autowired
    public HotelsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<HotelDto> findHotels() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(Url + "/hotel/read/all",
                        HotelDto[].class)))
                .collect(Collectors.toList());
    }

    @Override
    public HotelDto makeHotel(HotelDto hotelDto) {
        return restTemplate.postForObject(Url + "/hotel/create", hotelDto, HotelDto.class);
    }

    @Override
    public HotelDto updateTarget(Long id) {
        return restTemplate.getForObject(Url + "/hotel/read" + id, HotelDto.class);
    }

    @Override
    public void updatedHotel(HotelDto hotelDto) {
        restTemplate.put(Url + "/hotel/update" + hotelDto.getId(), hotelDto, HotelDto.class);
    }

    @Override
    public HotelDto findById(Long id) {
        return restTemplate.getForObject(Url + "/hotel/read/" + id, HotelDto.class);
    }

    @Override
    public void deleteHotel(String id) {
        restTemplate.delete(Url+ "/hotel/delete/" + id);
    }
}