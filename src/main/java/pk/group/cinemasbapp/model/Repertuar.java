package pk.group.cinemasbapp.model;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repertuar {

    private Long id;

    private String genre;

    private String title;

    private String img;

    private List<LocalTime> timeList;
}
