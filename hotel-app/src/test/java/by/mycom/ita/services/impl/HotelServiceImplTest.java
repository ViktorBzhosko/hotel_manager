package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelRating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelDao hotelDao;

    @Mock
    private HotelRatingService hotelRatingService;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void whenCreate_thenOk() {
        Hotel hotel = createSimpleHotel();
        List<HotelRating> defaultRating = createDefaultRating();
        Mockito.when(hotelRatingService.createDefaultRating()).thenReturn(defaultRating);
        Mockito.when(hotelDao.save(Mockito.any())).thenReturn(hotel);
        Hotel actual = hotelService.create(hotel);
        Assertions.assertEquals(hotel, actual);
        Mockito.verify(hotelDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void whenCreate_ReturnException() {
        Hotel hotel = createSimpleHotelWithException();
        Assertions.assertThrows(DataIsIncorrectException.class,
                () -> hotelService.create(hotel));
    }

    @Test
    void whenReadAll_returnListHotels() {
        List<Hotel> list = new ArrayList<>();
        list.add(createSimpleHotel());
        list.add(createSimpleHotel());
        Mockito.when(hotelDao.findAll()).thenReturn(list);
        List<Hotel> actual = hotelService.readAll();
        Assertions.assertEquals(list, actual);
        Mockito.verify(hotelDao, Mockito.times(1)).findAll();
    }

    @Test
    void whenReadById_thenOk() {
        Hotel hotel = createSimpleHotel();
        Mockito.when(hotelDao.findById(10L)).thenReturn(Optional.ofNullable(hotel));
        Hotel expected = hotelService.readById(10L);
        Assertions.assertEquals(expected, hotel);
        Mockito.verify(hotelDao, Mockito.times(1)).findById(10L);
    }

    @Test
    void whenUpdate_thenReturnHotel() {
        Hotel hotel = createSimpleHotel();
        Hotel updatedHotel = createSimpleHotel();
        updatedHotel.setName("Test");
        Mockito.when(hotelDao.findById(1L)).thenReturn(Optional.of(hotel));
        Mockito.when(hotelDao.save(hotel)).thenReturn(updatedHotel);
        Hotel expected = hotelService.update(1L, hotel);
        Assertions.assertEquals(expected, updatedHotel);
        Mockito.verify(hotelDao, Mockito.times(1)).findById(1L);
        Mockito.verify(hotelDao, Mockito.times(1)).save(hotel);
    }


    @Test
    void whenUpdate_returnException() {
        Hotel hotel = new Hotel();
        Mockito.when(hotelDao.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(DataNotFoundException.class, () -> hotelService.update(1L, hotel));
        Mockito.verify(hotelDao, Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        hotelDao.deleteById(1L);
        Mockito.verify(hotelDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void whenDeleteById_returnException() {
        Long id = 2L;
        doThrow(new DataNotFoundException()).when(hotelDao).deleteById(id);
        Assertions.assertThrows(DataNotFoundException.class, () -> hotelService.deleteById(id));

    }

    private Hotel createSimpleHotel() {
        return Hotel.builder()
                .name("Mercuri")
                .location("Egypt")
                .convenience("5 stars")
                .build();
    }

    private Hotel createSimpleHotelWithException() {
        return Hotel.builder()
                .name(null)
                .location("Egypt")
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }

    public List<HotelRating> createDefaultRating() {
        return IntStream.range(1, 6)
                .mapToObj(this::createRating)
                .collect(Collectors.toList());
    }

    private HotelRating createRating(Integer mark) {
        return HotelRating.builder()
                .mark(mark)
                .countOfMarks(0)
                .build();
    }
}