package by.mycom.ita.dao;

import by.mycom.ita.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room,Long> {
}
