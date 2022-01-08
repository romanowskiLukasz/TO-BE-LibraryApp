package pk.group.libraryapp.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllBorrowedBooksModel {
    Long id;
    String bookTitle;
    String bookAuthor;
    String userName;
    Long userId;
    Long bookId;
}
