package by.mycom.ita.services.impl;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.HotelDto;
import by.mycom.ita.services.IAuthentication;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookingCreateServiceImpl implements IBookingService {

    private final RestTemplate restTemplate;

    private final IAuthentication authentication;

    private final String Url = "http://localhost:8003/hotel-app/booking/";

    @Autowired
    public BookingCreateServiceImpl(RestTemplate restTemplate, IAuthentication authentication) {
        this.restTemplate = restTemplate;
        this.authentication = authentication;
    }

    @Override
    public BookingDto createBooking(Long hotelId, Long roomId, BookingDto bookingDto) {
        Long userId = authentication.getCurrentUserId();
        UriComponents buildUri = UriComponentsBuilder.fromHttpUrl(Url)
                .queryParam("hotelId", hotelId)
                .queryParam("roomId", roomId)
                .queryParam("userId", userId)
                .build();
        return restTemplate.postForObject(buildUri.toString(), bookingDto, BookingDto.class);
    }

    @Override
    public BookingDto updateForm(String id, Model model) {
        return restTemplate.getForObject(Url + id, BookingDto.class);
    }

    @Override
    public void updateByArrive(BookingDto bookingDto) {
        restTemplate.put(Url + "/update/arrive/" + bookingDto.getId(), BookingDto.class);

    }

    @Override
    public void updateByLeave(BookingDto bookingDto) {
        restTemplate.put(Url + "/update/leave/" + bookingDto.getId(), BookingDto.class);
    }

    @Override
    public void updateByCancelled(BookingDto bookingDto) {
        restTemplate.put(Url + "/update/canceled/" + bookingDto.getId(), BookingDto.class);
    }

    @Override
    public List<BookingDto> findAllBooking() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(Url + "/booking/find/all",
                        BookingDto[].class)))
                .collect(Collectors.toList());
    }
}
