package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.group.cinemasbapp.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
