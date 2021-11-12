package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.model.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
