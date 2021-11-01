package by.mycom.ita.model;

import by.mycom.ita.model.enums.BookingStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateChekIn;
    private LocalDate dateChekOut;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CommonUser users;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;

    @Enumerated
    private BookingStatus bookingStatus;


    
}
