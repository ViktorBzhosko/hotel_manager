package by.mycom.ita.services;

import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Room;

public interface IRoomService {

    Room create(Room room, Long hotelId) throws DataNotFoundException;

    Room update(Room room);

    Room findById(Long id);

    void deleteById(Long id);
}
