package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.BorrowedBooks;
import pk.group.libraryapp.entities.PublishingHouse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBooksRepo extends JpaRepository<BorrowedBooks, Long> {

}
