package pk.group.libraryapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.group.libraryapp.entities.User;
import pk.group.libraryapp.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    User findByEmail(String email);

    @Query("SELECT new pk.group.libraryapp.model.UserModel(u.id,u.name,u.email) from User u  where u.email=:email")
    public UserModel findModelByEmail(String email);
}
