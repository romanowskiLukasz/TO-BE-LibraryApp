package pk.group.cinemasbapp.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "seance")
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

    @OneToMany(mappedBy = "seance")
    private Set<Seat> seatSet;

}
