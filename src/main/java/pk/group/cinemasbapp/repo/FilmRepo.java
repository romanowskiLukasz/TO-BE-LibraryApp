package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.group.cinemasbapp.model.Film;

public interface FilmRepo extends JpaRepository<Film, Long> {

}
