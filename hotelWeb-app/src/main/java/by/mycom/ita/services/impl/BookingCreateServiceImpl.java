package by.mycom.ita.services.impl;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.services.IAuthentication;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BookingCreateServiceImpl implements IBookingService {

    private final RestTemplate restTemplate;

    private final IAuthentication authentication;

    private final String Url = "http://localhost:5438/testdb/booking/";

    @Autowired
    public BookingCreateServiceImpl(RestTemplate restTemplate, IAuthentication authentication) {
        this.restTemplate = restTemplate;
        this.authentication = authentication;
    }


    @Override
    public void createBooking(Long hotelId, Long roomId, BookingDto bookingDto, Model model) {
        Long userId = authentication.getCurrentUserId();
        UriComponents buildUri = UriComponentsBuilder.fromHttpUrl(Url)
                .queryParam("hotelId", hotelId)
                .queryParam("roomId", roomId)
                .queryParam("userId", userId)
                .build();

        BookingDto createdBooking = restTemplate.postForObject(buildUri.toString(), bookingDto, BookingDto.class);
        model.addAttribute("Booking", createdBooking);
    }

    @Override
    public void updateForm(String id, Model model) {
        BookingDto bookingDto = restTemplate.getForObject(Url + id, BookingDto.class);
        model.addAttribute("booking", bookingDto);
    }

    @Override
    public void updateByArrive(BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        restTemplate.put(Url + "/update/arrive/" + bookingDto.getId(), BookingDto.class);
    }

    @Override
    public void updateByLeave(BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        restTemplate.put(Url + "/update/leave/" + bookingDto.getId(), BookingDto.class);
    }

    @Override
    public void updateByCancelled(BookingDto bookingDto, Model model) {
        model.addAttribute("booking", bookingDto);
        restTemplate.put(Url + "/update/canceled/" + bookingDto.getId(), BookingDto.class);
    }
}
