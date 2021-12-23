package by.mycom.ita.services.impl;

import by.mycom.ita.configuration.ConfigClient;
import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.services.IAuthentication;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookingCreateServiceImpl implements IBookingService {

    private final RestTemplate restTemplate;

    private final IAuthentication authentication;

    private final ConfigClient client;
//    private final String Url = "http://localhost:8003/hotel-app";

    @Autowired
    public BookingCreateServiceImpl(RestTemplate restTemplate, IAuthentication authentication, ConfigClient client) {
        this.restTemplate = restTemplate;
        this.authentication = authentication;
        this.client = client;
    }


    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Long userId = authentication.getCurrentUserId();
        bookingDto.setUsers(CommonUserDto.builder()
                .id(userId)
                .build());

        return restTemplate.postForObject(client.serviceInfo() + "/booking/create", bookingDto, BookingDto.class);
    }

    @Override
    public BookingDto updateForm(String id, Model model) {
        return restTemplate.getForObject(client.serviceInfo()+ id, BookingDto.class);
    }

    @Override
    public void updateByArrive(BookingDto bookingDto) {
        restTemplate.put(client.serviceInfo()+"/booking/update/arrive/" + bookingDto.getId(), BookingDto.class);

    }

    @Override
    public void updateByLeave(BookingDto bookingDto) {
        restTemplate.put(client.serviceInfo()+"/booking/update/leave/" + bookingDto.getId(), BookingDto.class);
    }

    @Override
    public void updateByCancelled(BookingDto bookingDto) {
        restTemplate.put(client.serviceInfo()+"/booking/update/canceled/" + bookingDto.getId(), BookingDto.class);

    }

    @Override
    public List<BookingDto> findAllBooking() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(client.serviceInfo()+"/find/all",
                        BookingDto[].class)))
                .collect(Collectors.toList());
    }
}
