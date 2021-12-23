package by.mycom.ita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonUserDto {

    private long id;
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String firstName;

    @NotBlank(message = "Second should not be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10 characters")
    private String secondName;

    @NotBlank(message = "Passport should not be empty")
    private String passport;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Please enter your phone number")
    private int phoneNumber;

    private BookingDto booking;
}
