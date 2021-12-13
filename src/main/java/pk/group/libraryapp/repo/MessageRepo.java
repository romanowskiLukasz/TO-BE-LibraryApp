package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Messages;


@Repository
public interface MessageRepo extends JpaRepository<Messages, Long> {

}
