package by.mycom.ita.services;

import by.mycom.ita.model.Hotel;

import java.util.List;

public interface IHotelFilterService {

    List<Hotel> filterByName(String name);

    List<Hotel> filterByLocation(String location);

    List<Hotel> filterByRanking();

}
