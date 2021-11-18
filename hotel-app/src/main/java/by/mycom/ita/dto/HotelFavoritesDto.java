package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelFavoritesDto {

    private Long id;
    private HotelDto hotel;
    private CommonUserDto user;
}
