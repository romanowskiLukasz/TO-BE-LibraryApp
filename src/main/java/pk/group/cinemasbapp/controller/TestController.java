package pk.group.cinemasbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pk.group.cinemasbapp.entities.*;
import pk.group.cinemasbapp.model.*;
import pk.group.cinemasbapp.service.CinemaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TestController {
    
    CinemaService cinemaService;

    @Autowired
    public TestController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/reservation")
    public void postReservation(@RequestBody ReservationModel reservationModel) {
        cinemaService.postReservation(reservationModel);
    }

    @PostMapping("/user/register")
    public void registerUser(@RequestBody RegisterModel registerModel){
        cinemaService.registerUser(registerModel);
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestBody LoginModel loginModel){
        return cinemaService.loginUser(loginModel);
    }

    @GetMapping("/reservation/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable String userId){
        return cinemaService.getReservationsByUserId(Long.parseLong(userId));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return cinemaService.getAllUsers();
    }

    @GetMapping("/films")
    public List<FilmModel> getAllFilms() {
        return cinemaService.getAllFilms();
    }

    @GetMapping("/films/{filmId}")
    public Film getFilmByID(@PathVariable String filmId){
        return cinemaService.getFilmById(Long.parseLong(filmId));
    }

    @GetMapping("/seanceroom")
    public SeanceRoomModel getSeanceRoom(@RequestParam String filmId,
                                         @RequestParam String date,
                                         @RequestParam String time) {
        return cinemaService.getSeanceRoom(filmId, date, time);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return cinemaService.getAllRooms();
    }

    @GetMapping("/seances")
    public List<Seance> getAllSeances() {
        return cinemaService.getAllSeances();
    }

    @GetMapping("/seances/{day}")
    public List<Seance> getSeancesByDay(@PathVariable String day) {
        return cinemaService.getSeancesByDay(LocalDate.parse(day));
    }

    @GetMapping("/repertuar/{date}")
    public List<Repertuar> getRepertuarByDate(@PathVariable String date) {
        return cinemaService.getRepertuarByDate(LocalDate.parse(date));
    }

   

}
