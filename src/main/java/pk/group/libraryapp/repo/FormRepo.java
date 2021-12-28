package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Form;
import pk.group.libraryapp.entities.Messages;
import pk.group.libraryapp.entities.User;


@Repository
public interface FormRepo extends JpaRepository<Form, Long> {

}
