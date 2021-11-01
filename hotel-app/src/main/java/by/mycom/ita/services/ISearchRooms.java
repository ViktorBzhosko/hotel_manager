package by.mycom.ita.services;

import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface ISearchRooms {

    List<Booking> findBookingRooms(LocalDate dateChekIn, LocalDate dateChekOut);

    List<Room> findEmptyRooms(LocalDate dateChekIn, LocalDate dateChekOut);

}
