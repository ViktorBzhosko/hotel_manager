package by.mycom.ita.dto;

import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {

    private long id;
    private int numberOfRoom;
    private HotelDto hotel;
    private BookingDto booking;

    @Enumerated(EnumType.STRING)
    private Accommodation accommodation;

    @Enumerated(EnumType.STRING)
    private Comfort comfort;
}
