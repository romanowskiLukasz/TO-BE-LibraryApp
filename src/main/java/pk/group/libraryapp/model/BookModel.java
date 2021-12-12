package pk.group.libraryapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookModel {

    private String title;
    private String genre;
    private String img;
    private String description;
    private String name;
}
