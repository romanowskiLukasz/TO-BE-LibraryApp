package pk.group.cinemasbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pk.group.cinemasbapp.model.*;
import pk.group.cinemasbapp.repo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cinema")
@CrossOrigin
public class TestController {

    UserRepo userRepo;
    FilmRepo filmRepo;
    RoomRepo roomRepo;
    SeanceRepo seanceRepo;
    SeatRepo seatRepo;

    @Autowired
    public TestController(UserRepo userRepo, FilmRepo filmRepo, RoomRepo roomRepo, SeanceRepo seanceRepo, SeatRepo seatRepo) {
        this.userRepo = userRepo;
        this.filmRepo = filmRepo;
        this.roomRepo = roomRepo;
        this.seanceRepo = seanceRepo;
        this.seatRepo = seatRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startDBTest(){
        User user = new User("Piotr", "Migda", "piotr16@fake.pl", "lubieAGH123");
        User user1 = new User("Kiotr", "Pigda", "kiotr16@fake.pl", "lubieUJ123");
        User user2 = new User("Riotr", "Zigda", "riotr16@fake.pl", "lubiePK123");
        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);

//        List<Seat> seatList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Seat seat = new Seat(false);
//            seatList.add(seat);
//            seatRepo.save(seat);
//        }

        Film film = new Film("Avengers: Wojna bez granic",
                LocalDate.parse("2018-04-26"), "Akcja", 149,
                "Na Ziemię przybywa wielki Thanos poszukujący artefaktów zwanych Kamieniami Nieskończoności");
        filmRepo.save(film);

        Room room = new Room(1);
        roomRepo.save(room);




    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        List<Film> films = filmRepo.findAll();
        return films;
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        List<Room> rooms = roomRepo.findAll();
        return rooms;
    }

}
