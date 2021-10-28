package by.mycom.ita.dto;

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
    private List<RoomDto> rooms;
    private List<HotelRatingDto> hotelRatings;
}
