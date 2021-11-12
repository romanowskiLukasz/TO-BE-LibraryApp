package pk.group.cinemasbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import pk.group.cinemasbapp.model.*;
import pk.group.cinemasbapp.repo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@CrossOrigin
public class TestController {

    UserRepo userRepo;
    FilmRepo filmRepo;
    RoomRepo roomRepo;
    SeanceRepo seanceRepo;
    SeatRepo seatRepo;
    ReservationRepo reservationRepo;

    @Autowired
    public TestController(UserRepo userRepo, FilmRepo filmRepo, RoomRepo roomRepo, SeanceRepo seanceRepo, SeatRepo seatRepo, ReservationRepo reservationRepo) {
        this.userRepo = userRepo;
        this.filmRepo = filmRepo;
        this.roomRepo = roomRepo;
        this.seanceRepo = seanceRepo;
        this.seatRepo = seatRepo;
        this.reservationRepo = reservationRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startDBTest() {
        User user = new User("Piotr", "Migda", "piotr16@fake.pl", "lubieAGH123");
        User user1 = new User("Kiotr", "Pigda", "kiotr16@fake.pl", "lubieUJ123");
        User user2 = new User("Riotr", "Zigda", "riotr16@fake.pl", "lubiePK123");
        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);

        Film film = new Film("Avengers: Wojna bez granic",
                LocalDate.parse("2018-04-26"), "Akcja", 149,
                "Na Ziemię przybywa wielki Thanos poszukujący artefaktów zwanych Kamieniami Nieskończoności");
        filmRepo.save(film);

        Room room = new Room(1);
        roomRepo.save(room);

        addSeance(film, room, LocalDate.parse("2021-12-20"), LocalTime.parse("10:15"));
        addSeance(film, room, LocalDate.parse("2021-12-20"), LocalTime.parse("13:15"));
        addSeance(film, room, LocalDate.parse("2021-12-20"), LocalTime.parse("16:20"));
        addSeance(film, room, LocalDate.parse("2021-12-21"), LocalTime.parse("10:15"));
        addSeance(film, room, LocalDate.parse("2021-12-21"), LocalTime.parse("15:15"));


    }

    public void addReservation(Seat seat, User user, Seance seance) {
        Reservation reservation = new Reservation(seat, user, seance);
        reservationRepo.save(reservation);
    }

    public void addSeance(Film film, Room room, LocalDate date, LocalTime time) {
        Seance seance = new Seance(film, room, date, time);
        seanceRepo.save(seance);

        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Seat seat = new Seat(false, seance);
            seatList.add(seat);
            seatRepo.save(seat);
        }
    }

    @PostMapping("/reservation")
    public void postReservation(@RequestParam Map<String, String> allParams) {
        Seat seat = seatRepo.findById(Long.parseLong(allParams.get("seatId"))).get();
        User user = userRepo.findById(Long.parseLong(allParams.get("userId"))).get();
        Seance seance = seanceRepo.findById(Long.parseLong(allParams.get("seanceId"))).get();
        Reservation reservation = new Reservation(seat, user, seance);
        seat.setTaken(true);
        seatRepo.save(seat);
        reservationRepo.save(reservation);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepo.findAll();
    }

    @GetMapping("/seanceroom")
    public SeanceRoom getSeanceRoom(@RequestParam String filmId,
                                    @RequestParam String date,
                                    @RequestParam String time) {
        Seance seance = seanceRepo.findByFilm_IdAndDateAndTime(Long.parseLong(filmId), LocalDate.parse(date), LocalTime.parse(time)).get();
        return SeanceRoom.builder()
                .seanceId(seance.getId())
                .roomId(seance.getRoom().getId())
                .seatList(seatRepo.findAllBySeance(seance))
                .build();
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @GetMapping("/seances")
    public List<Seance> getAllSeances() {
        List<Seance> seances = seanceRepo.findAll();
        return seances;
    }

    @GetMapping("/seances/{day}")
    public List<Seance> getSeancesByDay(@PathVariable String day) {
        return seanceRepo.findSeancesByDate(LocalDate.parse(day));
    }

    @GetMapping("/repertuar/{date}")
    public List<Repertuar> getRepertuarByDate(@PathVariable String date) {
        return prepareRepertuar(LocalDate.parse(date));
    }

    private List<Repertuar> prepareRepertuar(LocalDate date) {
        List<Seance> seancesAll = seanceRepo.findSeancesByDate(date);
        List<Film> films = seancesAll.stream().map(Seance::getFilm).distinct().collect(toList());

        List<Repertuar> repertuars = new ArrayList<>();

        films.forEach(film -> {
            Repertuar build = Repertuar.builder()
                    .id(film.getId())
                    .genre(film.getGenre())
                    .title(film.getTitle())
                    .img(film.getImg())
                    .timeList(seancesAll.stream()
                            .filter(seance ->
                                    seance.getFilm()
                                            .equals(film))
                            .map(Seance::getTime)
                            .collect(toList()))
                    .build();
            repertuars.add(build);
        });

        return repertuars;
    }

}
