package by.mycom.ita.dao;

import by.mycom.ita.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelFilterDao extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT * FROM Hotel ORDER BY LOCATION", nativeQuery = true)
    List<Hotel> filteringByLocation(@Param("LOCATION") String location);

    @Query(value = "SELECT * FROM Hotel ORDER BY NAME", nativeQuery = true)
    List<Hotel> filteringByName(@Param("NAME") String name);

    @Query(value = "SELECT * FROM Hotel ORDER BY avgMark DESC", nativeQuery = true)
    List<Hotel> filteringByRanking();

}
