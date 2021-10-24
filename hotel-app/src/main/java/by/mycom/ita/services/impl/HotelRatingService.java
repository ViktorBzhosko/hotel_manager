package by.mycom.ita.services.impl;

import by.mycom.ita.dao.HotelDao;
import by.mycom.ita.dao.HotelRatingDao;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.HotelRating;
import by.mycom.ita.services.IHotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HotelRatingService implements IHotelRatingService {

    private final HotelDao hotelDao;

    @Autowired
    public HotelRatingService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void estimateHotel(Long hotelId, Integer mark) {
        Hotel foundHotel = hotelDao.getById(hotelId);

        List<HotelRating> hotelRatings = foundHotel.getHotelRatings();

        HotelRating rating = hotelRatings.stream()
                .filter(hotelRating -> hotelRating.getMark().equals(mark))
                .findFirst()
                .orElseThrow(DataNotFoundException::new);
        rating.setCountOfMarks(rating.getCountOfMarks() + 1);

        double commonCount = hotelRatings.stream()
                .mapToInt(HotelRating::getCountOfMarks).sum();
        double ratingSum = hotelRatings.stream().mapToInt(hotelRating -> hotelRating.getMark() * hotelRating.getCountOfMarks())
                .sum();
        double avgMark = roundAvoid(ratingSum / commonCount, 1);

        foundHotel.setAvgMark(avgMark);

        hotelDao.save(foundHotel);
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    @Override
    public List<HotelRating> createDefaultRating() {
        return IntStream.range(1, 6)
                .mapToObj(this::createRating)
                .collect(Collectors.toList());
    }

    private HotelRating createRating(Integer mark) {
        return HotelRating.builder()
                .mark(mark)
                .countOfMarks(0)
                .build();

    }
}
