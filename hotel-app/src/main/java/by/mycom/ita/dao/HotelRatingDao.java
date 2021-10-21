package by.mycom.ita.dao;

import by.mycom.ita.model.HotelRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRatingDao extends JpaRepository<HotelRating, Long> {
}
