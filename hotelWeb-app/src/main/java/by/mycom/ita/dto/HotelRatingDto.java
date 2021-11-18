package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelRatingDto {

    private Long id;
    private Integer mark;
    private Integer countOfMarks;

}
