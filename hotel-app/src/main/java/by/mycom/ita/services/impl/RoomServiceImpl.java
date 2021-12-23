package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.dao.RoomDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomDao roomDao;
    private final HotelDao hotelDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.hotelDao = hotelDao;
    }

    @Transactional
    @Override
    public Room create(Room room, Long hotelId) throws DataNotFoundException {

        Hotel hotelFounded = hotelDao.findById(hotelId).orElseThrow(DataNotFoundException::new);
        List<Room> rooms = hotelFounded.getRooms();
        Room roomFounded = rooms.stream()
                .filter(r -> r.getNumberOfRoom() == room.getNumberOfRoom())
                .findFirst().orElseGet(() -> Room.builder()
                        .numberOfRoom(room.getNumberOfRoom())
                        .comfort(room.getComfort())
                        .hotels(hotelFounded)
                        .accommodation(room.getAccommodation())
                        .build());
        return roomDao.save(roomFounded);
    }

    @Override
    public Room update(Room room) {
        Room foundedRoom = roomDao.findById(room.getId()).orElseThrow(DataNotFoundException::new);
        foundedRoom.setNumberOfRoom(room.getNumberOfRoom());
        foundedRoom.setComfort(room.getComfort());
        foundedRoom.setAccommodation(room.getAccommodation());
        return roomDao.save(foundedRoom);
    }

    @Override
    public Room findById(Long roomId) {
        return roomDao.findById(roomId).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        roomDao.deleteById(id);
    }
}
