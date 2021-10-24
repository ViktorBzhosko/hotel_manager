package by.mycom.ita.dao;

import by.mycom.ita.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM Booking b WHERE Room.id BETWEEN b.dateChekIn AND b.dateChekOut ", nativeQuery = true)
    List<Booking> findBookingRooms(LocalDate dateChekIn, LocalDate dateChekOut);

    @Query(value = "SELECT * FROM Booking b WHERE Room.id NOT BETWEEN b.dateChekIn AND b.dateChekOut ", nativeQuery = true)
    List<Booking> findEmptyRooms(LocalDate dateChekIn, LocalDate dateChekOut);
}
