package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {
    private long id;
    private LocalDate dateChekIn;
    private LocalDate dateChekOut;
    private CommonUserDto usersDto;
    private RoomDto roomDto;
    private HotelDto hotelDto;
}
