package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.Form;
import pk.group.libraryapp.entities.Messages;
import pk.group.libraryapp.entities.User;
import pk.group.libraryapp.model.FormModel;

import java.util.List;


@Repository
public interface FormRepo extends JpaRepository<Form, Long> {
    @Query("SELECT new pk.group.libraryapp.model.FormModel(f.id,f.description,f.img,f.title,f.author,f.publishingHouse,f.user.id) from Form f  ")
    public List<FormModel> getAllForms();
}
