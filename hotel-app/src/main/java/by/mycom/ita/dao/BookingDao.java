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

    @Query(value = "SELECT b FROM Booking b WHERE (b.dateChekIn BETWEEN :dateChekIn AND :dateChekOut) or (b.dateChekOut BETWEEN :dateChekIn AND :dateChekOut) AND b.hotel.id= :hotelId AND b.bookingStatus= 0")
    List<Booking> findBookingRooms(Long hotelId, LocalDate dateChekIn, LocalDate dateChekOut);

    @Query(value = "SELECT r FROM Room r " +
            "WHERE r.id NOT IN (SELECT b.room.id FROM Booking b WHERE " +
            "(b.dateChekIn BETWEEN :dateChekIn AND :dateChekOut) or (b.dateChekOut BETWEEN :dateChekIn AND :dateChekOut) " +
            "AND b.hotel.id= :hotelId) " +
            "AND r.hotels.id= :hotelId")
    List<Room> findEmptyRooms(Long hotelId, LocalDate dateChekIn, LocalDate dateChekOut);
}
