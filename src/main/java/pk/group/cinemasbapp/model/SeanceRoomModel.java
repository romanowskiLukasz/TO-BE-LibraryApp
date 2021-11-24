package pk.group.cinemasbapp.model;

import lombok.*;
import pk.group.cinemasbapp.entities.Seat;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeanceRoomModel {

    private Long seanceId;

    private Long roomId;

    private List<Seat> seatList;
}
