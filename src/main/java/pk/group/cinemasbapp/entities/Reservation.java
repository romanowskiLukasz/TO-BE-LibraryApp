package pk.group.cinemasbapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private Seance seance;

    public Reservation(Seat seat, User user, Seance seance) {
        this.seat = seat;
        this.user = user;
        this.seance = seance;
    }
}