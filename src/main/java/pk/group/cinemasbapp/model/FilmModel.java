package pk.group.cinemasbapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmModel {

    private Long id;
    private String title;
    private String genre;
    private String img;
}
