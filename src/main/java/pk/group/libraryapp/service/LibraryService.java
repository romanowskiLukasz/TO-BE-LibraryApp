package pk.group.libraryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pk.group.libraryapp.entities.*;
import pk.group.libraryapp.model.*;
import pk.group.libraryapp.repo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@org.springframework.stereotype.Service
public class LibraryService {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    BookRepo bookRepo;
    UserRepo userRepo;
   /*
    FilmRepo filmRepo;
    RoomRepo roomRepo;
    SeanceRepo seanceRepo;
    SeatRepo seatRepo;
    ReservationRepo reservationRepo;*/

    @Autowired
    public LibraryService(BookRepo bookRepo,UserRepo userRepo/*, FilmRepo filmRepo, RoomRepo roomRepo, SeanceRepo seanceRepo, SeatRepo seatRepo, ReservationRepo reservationRepo*/) {
        this.bookRepo=bookRepo;
        this.userRepo = userRepo;
        /*
        this.filmRepo = filmRepo;
        this.roomRepo = roomRepo;
        this.seanceRepo = seanceRepo;
        this.seatRepo = seatRepo;
        this.reservationRepo = reservationRepo;*/
    }

    public List<BookModel> getBooksInfo() {
        return bookRepo.getBooksInfo();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void registerUser(RegisterModel registerModel) {
        User user = User.builder()
                .name(registerModel.getName())
                .email(registerModel.getEmail())
                .password(PASSWORD_ENCODER.encode(registerModel.getPassword()))
                .build();
        userRepo.save(user);
    }

    public String loginUser(LoginModel loginModel){
        User user = userRepo.findByEmail(loginModel.getEmail());
        if(user == null){
            return "2";
        }
        if(PASSWORD_ENCODER.matches(loginModel.getPassword(), user.getPassword())){
            return "0";
        }else {
            return "1";
        }
    }

    /*public void addReservation(Seat seat, User user, Seance seance) {
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

    public void postReservation(ReservationModel reservationModel) {
        Seat seat = seatRepo.findById(Long.parseLong(reservationModel.getSeatId())).get();
        User user = userRepo.findById(Long.parseLong(reservationModel.getUserId())).get();
        Seance seance = seanceRepo.findById(Long.parseLong(reservationModel.getSeanceId())).get();
        Reservation reservation = new Reservation(seat, user, seance);
        seat.setTaken(true);
        seatRepo.save(seat);
        reservationRepo.save(reservation);
    }



    public List<FilmModel> getAllFilms(){
        return filmRepo.findAll()
                .stream()
                .map(this::mapFilm)
                .collect(toList());
    }

    private FilmModel mapFilm(Film film){
        return FilmModel.builder()
                .id(film.getId())
                .title(film.getTitle())
                .genre(film.getGenre())
                .img(film.getImg())
                .build();
    }

    public Film getFilmById(Long id){
        return filmRepo.getById(id);
    }

    public SeanceRoomModel getSeanceRoom(String filmId, String date, String time) {
        Seance seance = seanceRepo.findByFilm_IdAndDateAndTime(Long.parseLong(filmId), LocalDate.parse(date), LocalTime.parse(time)).get();
        return SeanceRoomModel.builder()
                .seanceId(seance.getId())
                .roomId(seance.getRoom().getId())
                .seatList(seatRepo.findAllBySeance(seance))
                .build();
    }

    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }

    public List<Seance> getAllSeances() {
        return seanceRepo.findAll();
    }

    public List<Seance> getSeancesByDay(LocalDate day) {
        return seanceRepo.findSeancesByDate(day);
    }

    public List<Repertuar> getRepertuarByDate(LocalDate date) {
        return prepareRepertuar(date);
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





    public List<Reservation> getReservationsByUserId(Long userId){
        return reservationRepo.getAllByUser_Id(userId);
    }*/
}
