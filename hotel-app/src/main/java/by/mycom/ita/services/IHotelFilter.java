package by.mycom.ita.services;

import by.mycom.ita.model.Hotel;

import java.util.List;

public interface IHotelFilter {

    List<Hotel> coincidencesFiltering(Hotel hotel);

    List<Hotel> exactFiltering(Hotel hotel);
}
