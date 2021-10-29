package by.mycom.ita.services.impl;

import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IHotelServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String Url = "http://localhost:5438/testdb";

    @Autowired
    public HotelsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void findHotels(Model model) {
        List<HotelDto> hotels = Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(Url + "/hotel/read/all",
                        HotelDto[].class)))
                .collect(Collectors.toList());
        model.addAttribute("hotels", hotels);
    }

    @Override
    public void makeHotel(HotelDto hotelDto, Model model) {
        HotelDto creatHotel = restTemplate.postForObject(Url + "/hotel/create", hotelDto, HotelDto.class);
        model.addAttribute("Hotel", creatHotel);
    }
}
