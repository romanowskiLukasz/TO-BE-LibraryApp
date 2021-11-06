package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.model.Seat;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {

}
