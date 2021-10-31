package by.mycom.ita.dto;

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
    private String passport;
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
    private BookingDto bookingDto;
}