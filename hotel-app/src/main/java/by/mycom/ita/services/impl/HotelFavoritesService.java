package by.mycom.ita.services.impl;

import by.mycom.ita.dao.CommonUserDao;
import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.dao.HotelFavoritesDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelFavorites;
import by.mycom.ita.services.IHotelFavoritesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelFavoritesService implements IHotelFavoritesService {

    private final HotelFavoritesDao hotelFavoritesDao;
    private final CommonUserDao userDao;
    private final HotelDao hotelDao;

    public HotelFavoritesService(HotelFavoritesDao hotelFavoritesDao, CommonUserDao userDao, HotelDao hotelDao) {
        this.hotelFavoritesDao = hotelFavoritesDao;
        this.userDao = userDao;
        this.hotelDao = hotelDao;
    }

    @Override
    public CommonUser favorites(CommonUser user, Hotel hotel) {
        List<HotelFavorites> hotelFavoritesList = new ArrayList<>();
        Hotel foundHotel = hotelDao.findById(hotel.getId()).orElseThrow(DataNotFoundException::new);

        HotelFavorites hotelFavorites = HotelFavorites.builder()
                .hotel(foundHotel)
                .build();
        hotelFavoritesList.add(hotelFavorites);
        user.setHotelFavorites(hotelFavoritesList);
        return userDao.save(user);
    }

    @Override
    public List<HotelFavorites> showAllFavorites() {
        return hotelFavoritesDao.findAll();
    }

    @Override
    public void deleteFavorite(Long id) {
        hotelFavoritesDao.deleteById(id);
    }
}
