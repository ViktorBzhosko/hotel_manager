package by.mycom.ita.dao;

import by.mycom.ita.model.HotelFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelFavoritesDao extends JpaRepository<HotelFavorites, Long> {
}
