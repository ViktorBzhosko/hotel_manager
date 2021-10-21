package by.mycom.ita.dto;

import by.mycom.ita.model.Booking;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {

    private long id;
    private int numberOfRoom;
    private Hotel hotels;
    private Booking booking;
    private Accommodation accommodation;
    private Comfort comfort;
}
