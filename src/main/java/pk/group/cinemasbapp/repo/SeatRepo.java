package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.group.cinemasbapp.model.Seat;

public interface SeatRepo extends JpaRepository<Seat, Long> {

}
