package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDto {

    private long id;

    @NotBlank(message = "Name should not be empty")
    private String name;
    private Double avgMark;

    @NotBlank(message = "Location must be definitely")
    private String location;

    @NotBlank(message = "Enter the convenience")
    private String convenience;

    private List<RoomDto> rooms;
    private List<HotelRatingDto> hotelRatings;
}
