package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.entities.Reservation;
import pk.group.cinemasbapp.entities.User;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByUser_Id(Long user);
}
