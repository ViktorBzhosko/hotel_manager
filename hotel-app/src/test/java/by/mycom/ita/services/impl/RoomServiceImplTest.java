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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomDao roomDao;

    @Mock
    private HotelDao hotelDao;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void whenCreate_thenOk() {

//        Mockito.when(hotelDao.findById(1L)).thenReturn();

    }

    @Test
    void whenUpdate_returnRoom() {
        Room room = createSimpleRoom();
        Room updatedRoom = createSimpleRoom();
        updatedRoom.setNumberOfRoom(2);
        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(room));
        Mockito.when(roomDao.save(room)).thenReturn(updatedRoom);
        Room expected = roomService.update(1L, room);
        Assertions.assertEquals(expected, updatedRoom);
        Mockito.verify(roomDao, Mockito.times(1)).findById(1L);
        Mockito.verify(roomDao, Mockito.times(1)).save(room);

    }

    @Test
    void whenUpdate_returnException() {
        Room room = new Room();
        Mockito.when(roomDao.findById(20L)).thenReturn(Optional.empty());
        Assertions.assertThrows(DataNotFoundException.class, () -> roomService.update(20L, room));
        Mockito.verify(roomDao, Mockito.times(1)).findById(20L);
    }

    @Test
    void whenDelete_thenOk() {
        roomDao.deleteById(1L);
        Mockito.verify(roomDao, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void whenDelete_returnException() {
        Long id = 2L;
        doThrow(new DataNotFoundException()).when(roomDao).deleteById(id);
        Assertions.assertThrows(DataNotFoundException.class, () -> roomService.deleteById(id));
    }

    private Hotel createSimpleHotel(List<Room> rooms) {
        return Hotel.builder()
                .id(1L)
                .name("Mercuri")
                .location("Egypt")
                .rooms(rooms)
                .avgMark(4.5)
                .convenience("5 stars")
                .build();
    }

    private Room createSimpleRoom() {
        return Room.builder()
                .numberOfRoom(1)
                .accommodation(Accommodation.SINGLE)
                .comfort(Comfort.STANDARD)
                .build();
    }
}