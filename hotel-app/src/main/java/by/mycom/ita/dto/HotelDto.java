package by.mycom.ita.dto;

import by.mycom.ita.model.HotelRating;
import by.mycom.ita.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDto {

    private long id;
    private String name;
    private Double avgMark;
    private String location;
    private String convenience;
    private List<Room> rooms;
    private List<HotelRating> hotelRatings;
}
