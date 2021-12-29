package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.BorrowedBooks;
import pk.group.libraryapp.model.UserBorrowModel;

import java.util.List;

@Repository
public interface BorrowedRepo extends JpaRepository<BorrowedBooks,Long> {
    @Query("SELECT new pk.group.libraryapp.model.UserBorrowModel(b.title,a.name,r.reservationDate,r.returnDate) from BorrowedBooks r join r.book b join  b.authors a where r.user.id=:userId")
    List<UserBorrowModel> getUserBorrowedBook(Long userId);
}



