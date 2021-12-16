package by.mycom.ita.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "common_user")
public class CommonUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String firstName;
    private String secondName;
    private String passport;
    private String email;
    private Integer phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="booking_id")
    private Booking booking;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<HotelFavorites> hotelFavorites;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonUser user = (CommonUser) o;
        return Objects.equals(firstName, user.firstName)
                && Objects.equals(secondName, user.secondName)
                && Objects.equals(passport, user.passport)
                && Objects.equals(email, user.email)
                && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, passport, email, phoneNumber);
    }
}
