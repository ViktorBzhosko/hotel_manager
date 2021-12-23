package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommonUserDto {

    private long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String firstName;

    @NotEmpty(message = "Second name should not be empty")
    @Size(min = 2, max = 20, message = "Second name should be between 2 and 20 characters")
    private String secondName;

    @NotEmpty(message = "Passport should not be empty")
    private String passport;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    private int phoneNumber;

    @NotEmpty(message = "Name should not be empty")
    private String username;

    @NotEmpty(message = "Name should not be empty")
    private String password;
    private BookingDto bookingDto;
}