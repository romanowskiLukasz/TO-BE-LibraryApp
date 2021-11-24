package pk.group.cinemasbapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "films")
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
    @Lob
    private String description;

    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
    private Set<Seance> seanceSet;

    public Film(String title, LocalDate premiere, String genre, int duration, String description) {
        this.title = title;
        this.premiere = premiere;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
    }

    public Film(String title, LocalDate premiere, String genre, int duration, String description, String img) {
        this.title = title;
        this.premiere = premiere;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
        this.img = img;
    }
}
