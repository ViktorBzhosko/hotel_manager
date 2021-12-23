package by.mycom.ita.dto;

import by.mycom.ita.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateChekIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateChekOut;

    private CommonUserDto users;
    private RoomDto room;
    private HotelDto hotel;

    @Enumerated
    private BookingStatus bookingStatus;

}
