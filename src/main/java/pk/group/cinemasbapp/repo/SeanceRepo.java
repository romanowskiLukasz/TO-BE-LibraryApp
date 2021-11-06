package pk.group.cinemasbapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pk.group.cinemasbapp.model.Seance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeanceRepo extends JpaRepository<Seance, Long> {
    List<Seance> findSeancesByDate(LocalDate date);

    //@Query(value = "SELECT * FROM people WHERE people.name LIKE :pattern", nativeQuery = true)
    //List<Seance> findPeopleWithNameStartingWith(@Param("pattern") String pattern);
}
