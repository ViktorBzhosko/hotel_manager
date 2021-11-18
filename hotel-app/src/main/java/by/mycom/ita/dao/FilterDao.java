package by.mycom.ita.dao;

import by.mycom.ita.model.Hotel;

import java.util.List;

public interface FilterDao {

    List<Hotel> coincidencesFiltering(Hotel hotel);

    List<Hotel> exactFiltering(Hotel hotel);
}
