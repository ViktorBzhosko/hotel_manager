package by.mycom.ita.services;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Room;

import java.util.List;

public interface IRoomService {

    List<Room> create(Room room, Long hotelId) throws DataIsIncorrectException;

    List<Room> readAll() throws DataNotFoundException;

    Room readById(Long id) throws DataNotFoundException;

    Room update(Long id, Room room);

    void deleteById(Long id);
}
