package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Book;
import pk.group.libraryapp.model.BookModel;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

//    @Query("SELECT new pk.group.libraryapp.model.BookModel(b.title,b.genre,b.img,b.description,a.name) from Book b join b.bookAuthor ba join ba.author a ")
//    public List<BookModel> getBooksInfo();
}
