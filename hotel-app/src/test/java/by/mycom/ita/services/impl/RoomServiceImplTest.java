package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.dao.RoomDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoomServiceImplTest {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomServiceImpl roomService;

    @Test
    void whenCreate_thenOk() {

        Room simpleRoom1 = createSimpleRoom(1);
        Room simpleRoom2 = createSimpleRoom(2);

        List<Room> rooms1 = new ArrayList<>(List.of(simpleRoom1));

        Hotel simpleHotel1 = createSimpleHotel(rooms1, 1L, "Mercuri");
        Hotel simpleHotel2 = createSimpleHotel(List.of(), 2L, "Alladin");

        hotelDao.save(simpleHotel1);
        hotelDao.save(simpleHotel2);

        Room room11 = roomService.create(simpleRoom2, 1L);
        Room room22 = roomService.create(simpleRoom2, 2L);

        Assertions.assertEquals(room11, simpleRoom2);
        Assertions.assertEquals(room22, simpleRoom2);

    }

    @Test
    void whenCreate_thenException() {
        Room simpleRoom = createSimpleRoom(1);
        Mockito.when(hotelDao.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(DataNotFoundException.class, () -> roomService.create(simpleRoom, 1L));
    }

    @Test
    void whenUpdate_returnRoom() {
        Room room = createSimpleRoom(1);
        Room updatedRoom = createSimpleRoom(2);
        updatedRoom.setId(1);
        updatedRoom.setComfort(Comfort.BUSINESS);
        roomDao.save(room);
        Room expected = roomService.update(updatedRoom);
        Assertions.assertEquals(expected, updatedRoom);
    }

    @Test
    void whenUpdate_returnException() {
        Room room = createSimpleRoom(10);
        Assertions.assertThrows(DataNotFoundException.class, () -> roomService.update(room));
    }

    private Hotel createSimpleHotel(List<Room> rooms, Long id, String name) {
        return Hotel.builder()
                .id(id)
                .name(name)
                .location("Egypt")
                .rooms(rooms)
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }

    private Room createSimpleRoom(Integer numberOfRoom) {
        return Room.builder()
                .numberOfRoom(numberOfRoom)
                .accommodation(Accommodation.SINGLE)
                .comfort(Comfort.STANDARD)
                .build();
    }
}