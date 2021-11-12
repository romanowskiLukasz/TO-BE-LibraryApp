package pk.group.cinemasbapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<Seance> seanceSet;

    public Room(int number) {
        this.number = number;
    }
}
