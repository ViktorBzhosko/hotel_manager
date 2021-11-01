package by.mycom.ita.dao;

import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM Booking b WHERE b.date_chek_in >= :dateChekIn AND b.date_chek_out <= :dateChekOut", nativeQuery = true)
    List<Booking> findBookingRooms(LocalDate dateChekIn, LocalDate dateChekOut);

    @Query(value = "SELECT r FROM Room r " +
            "WHERE r.id NOT IN (SELECT b.room.id FROM Booking b WHERE b.dateChekIn >= :dateChekIn AND b.dateChekOut <= :dateChekOut)")
    List<Room> findEmptyRooms(LocalDate dateChekIn, LocalDate dateChekOut);
}
