package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelRating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HotelRatingServiceTest {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    HotelRatingService hotelRatingService;

    @Test
    void whenEstimateHotel_thenReturnAvgMark() {

        List<HotelRating> defaultRating = createHotelRatingList();
        Hotel simpleHotel = createSimpleHotel(defaultRating);
        hotelDao.save(simpleHotel);

        Hotel hotel = hotelRatingService.estimateHotel(1L, 5);
        Double avgMark = hotel.getAvgMark();
        Assertions.assertEquals(avgMark, 4D);

    }

    private List<HotelRating> createHotelRatingList() {
        HotelRating rating1 = createHotelRating(5, 1);
        HotelRating rating2 = createHotelRating(4, 2);
        HotelRating rating3 = createHotelRating(3, 2);
        HotelRating rating4 = createHotelRating(1, 0);
        return List.of(rating1, rating2, rating3, rating4);
    }

    private HotelRating createHotelRating(Integer mark, Integer count) {
        return HotelRating.builder()
                .mark(mark)
                .countOfMarks(count)
                .build();
    }

    private Hotel createSimpleHotel(List<HotelRating> defaultRating) {
        return Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .avgMark(5.0)
                .hotelRatings(defaultRating)
                .convenience("5 stars")
                .build();
    }

}