package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {



}