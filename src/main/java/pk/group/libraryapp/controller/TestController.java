package pk.group.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pk.group.libraryapp.entities.*;
import pk.group.libraryapp.model.*;
import pk.group.libraryapp.service.LibraryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {
    
    LibraryService libraryService;

    @Autowired
    public TestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public List<BookModel> getBooksInfo(){
        return libraryService.getBooksInfo();
    }

    @PostMapping("/user/register")
    public void registerUser(@RequestBody RegisterModel registerModel){
        libraryService.registerUser(registerModel);
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestBody LoginModel loginModel){
        return libraryService.loginUser(loginModel);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return libraryService.getAllUsers();
    }

    @PostMapping("/user/message")
    public void addMessage(@RequestBody Messages message){
        libraryService.addMessage(message);
    }

    /*
    @PostMapping("/reservation")
    public void postReservation(@RequestBody ReservationModel reservationModel) {
        libraryService.postReservation(reservationModel);
    }



    @GetMapping("/reservation/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable String userId){
        return libraryService.getReservationsByUserId(Long.parseLong(userId));
    }



    @GetMapping("/films")
    public List<FilmModel> getAllFilms() {
        return libraryService.getAllFilms();
    }

    @GetMapping("/films/{filmId}")
    public Film getFilmByID(@PathVariable String filmId){
        return libraryService.getFilmById(Long.parseLong(filmId));
    }

    @GetMapping("/seanceroom")
    public SeanceRoomModel getSeanceRoom(@RequestParam String filmId,
                                         @RequestParam String date,
                                         @RequestParam String time) {
        return libraryService.getSeanceRoom(filmId, date, time);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return libraryService.getAllRooms();
    }

    @GetMapping("/seances")
    public List<Seance> getAllSeances() {
        return libraryService.getAllSeances();
    }

    @GetMapping("/seances/{day}")
    public List<Seance> getSeancesByDay(@PathVariable String day) {
        return libraryService.getSeancesByDay(LocalDate.parse(day));
    }

    @GetMapping("/repertuar/{date}")
    public List<Repertuar> getRepertuarByDate(@PathVariable String date) {
        return libraryService.getRepertuarByDate(LocalDate.parse(date));
    }
    */

   

}
