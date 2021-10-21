package by.mycom.ita.dto;

import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelFavoritesDto {

    private Long id;
    private Hotel hotel;
    private CommonUser user;
}
