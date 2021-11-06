package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.dao.HotelFavoritesDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HotelFavoritesService implements IHotelFavoritesService {

    private final HotelFavoritesDao hotelFavoritesDao;
    private final CommonUserDao userDao;
    private final HotelDao hotelDao;

    @Autowired
    public HotelFavoritesService(HotelFavoritesDao hotelFavoritesDao, CommonUserDao userDao, HotelDao hotelDao) {
        this.hotelFavoritesDao = hotelFavoritesDao;
        this.userDao = userDao;
        this.hotelDao = hotelDao;
    }

    @Transactional
    @Override
    public HotelFavorites favorites(Long userId, Hotel hotel) {
        CommonUser user = userDao.findById(userId).orElseThrow(DataNotFoundException::new);
        Hotel foundHotel = hotelDao.findById(hotel.getId()).orElseThrow(DataNotFoundException::new);
        List<HotelFavorites> favoritesList = hotelFavoritesDao.findByUserId(userId);

        Optional<HotelFavorites> findFavorite = favoritesList.stream()
                .filter(favorite -> favorite.getHotel().getId() == hotel.getId())
                .findFirst();

        if (findFavorite.isEmpty()) {
            HotelFavorites hotelFavorites = HotelFavorites.builder()
                    .user(user)
                    .hotel(foundHotel)
                    .build();
            return hotelFavoritesDao.save(hotelFavorites);
        }
        return findFavorite.orElseThrow(RuntimeException::new);
    }

    @Override
    public List<HotelFavorites> showAllFavorites(Long userId) {
        CommonUser user = userDao.findById(userId).orElseThrow(DataNotFoundException::new);
        return user.getHotelFavorites();
    }

}
