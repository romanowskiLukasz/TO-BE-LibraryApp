package pk.group.cinemasbapp.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "seances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Room room;

    private LocalDate date;

    private LocalTime time;

    @OneToMany(mappedBy = "seance")
    private List<Seat> seatSet;

    public Seance(Film film, Room room, LocalDate date, LocalTime time) {
        this.film = film;
        this.room = room;
        this.date = date;
        this.time = time;
    }
}
