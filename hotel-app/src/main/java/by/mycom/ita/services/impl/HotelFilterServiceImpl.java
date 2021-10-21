package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelFilterDao;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelFilterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelFilterServiceImpl implements IHotelFilterService {

    private final HotelFilterDao hotelFilterDao;

    public HotelFilterServiceImpl(HotelFilterDao hotelFilterDao) {
        this.hotelFilterDao = hotelFilterDao;
    }

    @Override
    public List<Hotel> filterByName(String name) {
        return hotelFilterDao.filteringByName(name);
    }

    @Override
    public List<Hotel> filterByLocation(String location) {
        return hotelFilterDao.filteringByLocation(location);
    }

    @Override
    public List<Hotel> filterByRanking() {
        return hotelFilterDao.filteringByRanking();
    }
}
