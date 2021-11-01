package by.mycom.ita.model;

import by.mycom.ita.model.enums.Accommodation;
import by.mycom.ita.model.enums.Comfort;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    private int numberOfRoom;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Hotel hotels;

    @Enumerated(EnumType.STRING)
    private Accommodation accommodation;
    @Enumerated(EnumType.STRING)
    private Comfort comfort;

}
