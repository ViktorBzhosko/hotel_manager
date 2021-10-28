package by.mycom.ita.services.impl;

import by.mycom.ita.dto.BookingDto;
import by.mycom.ita.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

public class BookingWCreateImpl implements IBookingService {

    private final RestTemplate restTemplate;

    private final String Url = "http://localhost:5438/testdb/booking/";

    @Autowired
    public BookingWCreateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createBooking(Long id, Integer number, BookingDto bookingDto, Model model) {

        BookingDto createdBooking = restTemplate.postForObject();
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
