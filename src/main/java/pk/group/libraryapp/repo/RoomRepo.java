package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Form;

@Repository
public interface RoomRepo extends JpaRepository<Form, Long> {

}
