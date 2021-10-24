package by.mycom.ita.services;

import by.mycom.ita.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface ISearchRooms {

    List<Booking> findBookingRooms(LocalDate dateChekIn, LocalDate dateChekOut);

    List<Booking> findEmptyRooms(LocalDate dateChekIn, LocalDate dateChekOut);

}
