package pk.group.cinemasbapp.model;

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
