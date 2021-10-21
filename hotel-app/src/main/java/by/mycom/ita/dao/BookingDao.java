package by.mycom.ita.dao;

import by.mycom.ita.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDao extends JpaRepository<Booking,Long> {
}
