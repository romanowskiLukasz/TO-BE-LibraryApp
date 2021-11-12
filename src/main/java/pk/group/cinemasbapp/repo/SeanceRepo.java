package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.model.Film;
import pk.group.cinemasbapp.model.Seance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepo extends JpaRepository<Seance, Long> {
    List<Seance> findSeancesByDate(LocalDate date);
    Optional<Seance> findById(Long id);
    Optional<Seance> findByFilm_IdAndDateAndTime(Long filmId, LocalDate date, LocalTime time);

    //@Query(value = "SELECT * FROM people WHERE people.name LIKE :pattern", nativeQuery = true)
    //List<Seance> findPeopleWithNameStartingWith(@Param("pattern") String pattern);
}
