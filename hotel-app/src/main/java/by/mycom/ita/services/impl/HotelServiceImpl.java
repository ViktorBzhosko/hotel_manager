package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelRatingService;
import by.mycom.ita.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {

    private final HotelDao hotelDao;
    private final IHotelRatingService hotelRatingService;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao, IHotelRatingService hotelRatingService) {
        this.hotelDao = hotelDao;
        this.hotelRatingService = hotelRatingService;
    }

    @Override
    public Hotel create(Hotel hotel) throws DataIsIncorrectException {
        Hotel createHotel = Hotel.builder()
                .name(hotel.getName())
                .location(hotel.getLocation())
                .avgMark(0D)
                .hotelRatings(hotelRatingService.createDefaultRating())
                .convenience(hotel.getConvenience())
                .build();
        return hotelDao.save(createHotel);
    }

    @Override
    public List<Hotel> readAll() {
        return hotelDao.findAll();

    }

    @Override
    public Hotel readById(Long id) throws RuntimeException {
        if (id < 1) throw new DataIsIncorrectException();
        return hotelDao.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Hotel update(Long id, Hotel hotel) {
        Hotel foundedHotel = hotelDao.findById(id).orElseThrow(DataNotFoundException::new);
        foundedHotel.setName(hotel.getName());
        foundedHotel.setLocation(hotel.getLocation());
        foundedHotel.setConvenience(hotel.getConvenience());
        return hotelDao.save(foundedHotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelDao.deleteById(id);
    }
}
