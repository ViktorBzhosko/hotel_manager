package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommonUserDto {

    private long id;
    private String firstName;
    private String secondName;
    @NotBlank(message = "ENTER THIS FIELD")
    private String passport;
    @Email(message = "ENTER THIS FIELD")
    private String email;
    private int phoneNumber;
    private String username;
    private String password;
    private BookingDto bookingDto;
}