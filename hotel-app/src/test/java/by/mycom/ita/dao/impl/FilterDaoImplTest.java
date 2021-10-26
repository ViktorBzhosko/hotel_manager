package by.mycom.ita.dao.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.impl.HotelFilterServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilterDaoImplTest {

    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private HotelFilterServiceImpl hotelFilterService;

    @Test
    void coincidencesFiltering() {
        hotelDao.save(createOtherHotel());
        List<Hotel> hotels = hotelFilterService.exactFiltering(createHotel());
        Assertions.assertEquals("Mercuri",hotels.get(0).getName());
    }

    @Test
    void exactFiltering() {
    }


    private Hotel createOtherHotel() {
        return Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }
    private Hotel createHotel() {
        return Hotel.builder()
                .name("Mercuri")
                .build();
    }
}