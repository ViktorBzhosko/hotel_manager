package by.mycom.ita.dto;

import by.mycom.ita.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonUserDto {

    private long id;
    private String firstName;
    private String secondName;
    private char passport;
    private char email;
    private int phoneNumber;
    private Booking booking;
}
