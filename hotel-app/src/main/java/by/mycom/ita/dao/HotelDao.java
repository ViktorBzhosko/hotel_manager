package by.mycom.ita.dao;

import by.mycom.ita.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel, Long> {
}
