package by.mycom.ita.services.impl;

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

    private final String Url = "http://hotel-app:8003/hotel-app/booking";

    @Autowired
    public BookingCreateServiceImpl(RestTemplate restTemplate, IAuthentication authentication) {
        this.restTemplate = restTemplate;
        this.authentication = authentication;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Long userId = authentication.getCurrentUserId();
        bookingDto.setUsers(CommonUserDto.builder()
                .id(userId)
                .build());

        return restTemplate.postForObject(Url + "/create", bookingDto, BookingDto.class);
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
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(Url + "/find/all",
                        BookingDto[].class)))
                .collect(Collectors.toList());
    }
}
