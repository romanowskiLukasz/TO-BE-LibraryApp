package pk.group.cinemasbapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_taken")
    private boolean isTaken;

    @JsonIgnore
    @ManyToOne
    Seance seance;

    public Seat(boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Seat(boolean isTaken, Seance seance) {
        this.isTaken = isTaken;
        this.seance = seance;
    }
}
