package by.mycom.ita.services.impl;

import by.mycom.ita.dao.RoomDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
import by.mycom.ita.services.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomDao roomDao;

    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room create(Room room, int roomNumber) throws DataIsIncorrectException {
        Room newRoom = Room.builder()
                .numberOfRoom(roomNumber)
                .comfort(Comfort.FAMILY)
                .accommodation(Accommodation.DOUBLE)
                .build();
        return roomDao.save(newRoom);
    }

    @Override
    public List<Room> readAll() throws DataNotFoundException {
        List<Room> rooms = roomDao.findAll();
        if (!rooms.isEmpty()) return rooms;
        else throw new DataNotFoundException();
    }

    @Override
    public Room readById(Long id) throws DataNotFoundException {
        if (id < 1) throw new DataIsIncorrectException();
        return roomDao.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Room update(Long id, int roomNumber) {
        Room foundedRoom = roomDao.findById(id).orElseThrow(DataNotFoundException::new);
        foundedRoom.setNumberOfRoom(roomNumber);
        foundedRoom.setComfort(Comfort.FAMILY);
        foundedRoom.setAccommodation(Accommodation.DOUBLE);
        return roomDao.save(foundedRoom);
    }

    @Override
    public void deleteById(Long id) {
        roomDao.deleteById(id);
    }
}
