package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
