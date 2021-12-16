package by.mycom.ita.services.impl;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.RoomDto;
import by.mycom.ita.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SearchRoomsService implements ISearchService {

    private final RestTemplate restTemplate;

    String Url = "http://localhost:8003/hotel-app";

    @Autowired
    public SearchRoomsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BookingDto> findBookingRooms(BookingDto bookingDto, Model model) {
        ResponseEntity<BookingDto[]> responseEntity =
                restTemplate.postForEntity(Url + "/search/booking", bookingDto, BookingDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public List<RoomDto> findEmptyRooms(BookingDto bookingDto, Model model) {
        ResponseEntity<RoomDto[]> responseEntity =
                restTemplate.postForEntity(Url + "/search/empty", bookingDto, RoomDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }
}
