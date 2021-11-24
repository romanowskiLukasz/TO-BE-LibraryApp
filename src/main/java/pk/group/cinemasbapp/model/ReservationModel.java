package pk.group.cinemasbapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationModel {
    private String seatId;
    private String userId;
    private String seanceId;
}
