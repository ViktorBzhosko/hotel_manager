package by.mycom.ita.dao.impl;

import by.mycom.ita.dao.FilterDao;
import by.mycom.ita.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilterDaoImplTest {

    @Autowired
    private FilterDao filterDao;

    @Test
    void whenFilter_thenReturnFilteredHotel() {
        Hotel hotel = getHotel();
        Hotel filteredHotel = filterDao.exactFiltering(hotel).get(0);
        Assertions.assertEquals("Aladdin", filteredHotel.getName());
        Assertions.assertEquals("Egypt", filteredHotel.getLocation());
    }

    private Hotel getHotel() {
        return Hotel.builder()
                .name("Aladdin")
                .location("Egypt")
                .build();
    }
}
