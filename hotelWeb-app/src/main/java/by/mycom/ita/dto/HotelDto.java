package by.mycom.ita.dto;

import by.mycom.ita.dto.enums.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelDto {
    private long id;

    private String name;

    private Double avgMark;

    private String location;

    private String convenience;

    private List<RoomDto> rooms;

    private List<HotelRatingDto> hotelRatings;

    @Enumerated(EnumType.STRING)
    private Weather weather;
}
