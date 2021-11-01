package by.mycom.ita.dao;

import by.mycom.ita.model.HotelFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelFavoritesDao extends JpaRepository<HotelFavorites, Long> {
   List<HotelFavorites> findByUserId(Long id);
}
