package pk.group.cinemasbapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Films")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "premiere_date", columnDefinition = "DATE")
    private LocalDate premiere;

    @Column(name = "genre")
    private String genre;

    @Column(name = "duration")
    private int duration;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "film")
    private Set<Seance> seanceSet;

    public Film(String title, LocalDate premiere, String genre, int duration, String description) {
        this.title = title;
        this.premiere = premiere;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
    }
}
