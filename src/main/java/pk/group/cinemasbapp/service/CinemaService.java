package pk.group.cinemasbapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pk.group.cinemasbapp.entities.*;
import pk.group.cinemasbapp.model.*;
import pk.group.cinemasbapp.repo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@org.springframework.stereotype.Service
public class CinemaService {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    UserRepo userRepo;
    FilmRepo filmRepo;
    RoomRepo roomRepo;
    SeanceRepo seanceRepo;
    SeatRepo seatRepo;
    ReservationRepo reservationRepo;

    @Autowired
    public CinemaService(UserRepo userRepo, FilmRepo filmRepo, RoomRepo roomRepo, SeanceRepo seanceRepo, SeatRepo seatRepo, ReservationRepo reservationRepo) {
        this.userRepo = userRepo;
        this.filmRepo = filmRepo;
        this.roomRepo = roomRepo;
        this.seanceRepo = seanceRepo;
        this.seatRepo = seatRepo;
        this.reservationRepo = reservationRepo;
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

    public void postReservation(ReservationModel reservationModel) {
        Seat seat = seatRepo.findById(Long.parseLong(reservationModel.getSeatId())).get();
        User user = userRepo.findById(Long.parseLong(reservationModel.getUserId())).get();
        Seance seance = seanceRepo.findById(Long.parseLong(reservationModel.getSeanceId())).get();
        Reservation reservation = new Reservation(seat, user, seance);
        seat.setTaken(true);
        seatRepo.save(seat);
        reservationRepo.save(reservation);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
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

    public void registerUser(RegisterModel registerModel) {
        User user = User.builder()
                .name(registerModel.getName())
                .surname(registerModel.getSurname())
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

    public List<Reservation> getReservationsByUserId(Long userId){
        return reservationRepo.getAllByUser_Id(userId);
    }
}
