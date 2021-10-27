package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.group.cinemasbapp.model.Seance;

public interface SeanceRepo extends JpaRepository<Seance, Long> {

}
