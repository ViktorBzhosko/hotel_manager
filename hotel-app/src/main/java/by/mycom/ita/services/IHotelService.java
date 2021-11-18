package by.mycom.ita.services;

import by.mycom.ita.exception.DataIsIncorrectException;
import by.mycom.ita.exception.DataNotFoundException;
import by.mycom.ita.model.Hotel;

import java.util.List;

public interface IHotelService {

    Hotel create(Hotel hotel) throws DataIsIncorrectException;

    List<Hotel> readAll() ;

    Hotel readById(Long id) throws DataNotFoundException;

    Hotel update(Long id, Hotel hotel);

    void deleteById(Long id);
}


