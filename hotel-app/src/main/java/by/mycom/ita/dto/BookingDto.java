package by.mycom.ita.dto;

import by.mycom.ita.model.CommonUser;
import by.mycom.ita.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private long id;
    private LocalDateTime dateChekIn;
    private LocalDateTime dateChekOut;
    private CommonUser users;
    private List<Hotel> hotel;

}
