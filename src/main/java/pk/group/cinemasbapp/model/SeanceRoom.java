package pk.group.cinemasbapp.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeanceRoom {

    private Long seanceId;

    private Long roomId;

    private List<Seat> seatList;
}
