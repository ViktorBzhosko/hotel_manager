package by.mycom.ita.dto;

import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import by.mycom.ita.model.Room;
import by.mycom.ita.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private long id;
    private LocalDate dateChekIn;
    private LocalDate dateChekOut;
    private CommonUser users;
    private Room room;

    @Enumerated
    private BookingStatus bookingStatus;

}
