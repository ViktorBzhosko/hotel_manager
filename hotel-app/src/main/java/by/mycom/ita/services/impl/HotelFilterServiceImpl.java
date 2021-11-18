package by.mycom.ita.services.impl;

import by.mycom.ita.dao.FilterDao;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.services.IHotelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelFilterServiceImpl implements IHotelFilter {

    private final FilterDao filterDao;

    @Autowired
    public HotelFilterServiceImpl(FilterDao filterDao) {
        this.filterDao = filterDao;
    }

    @Override
    public List<Hotel> coincidencesFiltering(Hotel hotel) {
        return filterDao.coincidencesFiltering(hotel);
    }

    @Override
    public List<Hotel> exactFiltering(Hotel hotel) {
        return filterDao.exactFiltering(hotel);
    }
}
