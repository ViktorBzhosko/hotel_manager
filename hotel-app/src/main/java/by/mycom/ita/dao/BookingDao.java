package by.mycom.ita.dao;

import by.mycom.ita.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM Booking b WHERE b.date_chek_in >= :dateChekIn AND b.date_chek_out <= :dateChekOut", nativeQuery = true)
    List<Booking> findBookingRooms(LocalDate dateChekIn, LocalDate dateChekOut);

    @Query(value = "SELECT * FROM Room r " +
            "WHERE room_id NOT IN (SELECT * FROM Booking b WHERE b.date_chek_in >= :dateChekIn AND b.date_chek_out <= :dateChekOut)", nativeQuery = true)
    List<Booking> findEmptyRooms(LocalDate dateChekIn, LocalDate dateChekOut);
}
