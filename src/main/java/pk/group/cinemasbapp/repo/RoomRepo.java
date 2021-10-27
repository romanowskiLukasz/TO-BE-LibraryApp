package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.group.cinemasbapp.model.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
