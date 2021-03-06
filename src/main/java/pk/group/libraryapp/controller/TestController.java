package pk.group.libraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/popularBooks")
    public List<PopularBookModel> getPopularBooksInfo(){
        return libraryService.getPopularBooksInfo();
    }

    @GetMapping("/books/{bookId}")
    public BookModel getBookByID(@PathVariable String bookId){
        return libraryService.getBookById(Long.parseLong(bookId));
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
    public List<UserModel> getAllUsers() {
        return libraryService.getAllUsers();
    }

    @GetMapping("/me/{email}")
    public UserModel getUserInfo(@PathVariable String email) {
        return libraryService.getUserInfo(email);
    }

    @GetMapping("/reservations/{userId}")
    public List<UserReservationsModel> getReservations(@PathVariable String userId) {
        return libraryService.getReservations(Long.parseLong(userId));
    }

    @PostMapping("/user/message")
    public void addMessage(@RequestBody Messages message){
        libraryService.addMessage(message);
    }

    @PostMapping("/book/reservation")
    @ResponseBody
    public ResponseEntity  reserveBook(@RequestBody ReservationModel reservationModel){
        if(libraryService.reserveBook(reservationModel)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/rating")
    public void addRating(@RequestBody RatingModel ratingModel){
         libraryService.addRating(ratingModel);
    }

    @GetMapping("/ratings/{userId}")
    public List<RatingModel> getRatings(@PathVariable String userId) {
        return libraryService.getRatings(Long.parseLong(userId));
    }

    @PutMapping("user/changePassword")
    public String changePassword(@RequestBody ChangePasswordModel changePasswordModel){
        return libraryService.changePassword(changePasswordModel);
    }

    @PostMapping("/user/form")
    public void PostForm(@RequestBody FormModel form){
        libraryService.PostForm(form);
    }

    @PutMapping("/user/changeEmail")
    public String changeEmail(@RequestBody ChangeEmailModel changeEmailModel){
        return libraryService.changeEmail(changeEmailModel);
    }

    @GetMapping("/borrowedBooks/{userId}")
    public List<UserBorrowModel> getBorrowedBooks(@PathVariable String userId) {
        return libraryService.getBorrowedBooks(Long.parseLong(userId));
    }

    @GetMapping("/avgRatings")
    public List<AvgRatingModel> getAvgRatings() {
        return libraryService.getAvgRatings();
    }

    @DeleteMapping("/deleteForm/{formId}")
    public void deleteForm(@PathVariable String formId){
        libraryService.deleteForm(Long.parseLong(formId));
    }

    @GetMapping("/admin/getForms")
    public List<FormModel> getForms() {
        return libraryService.getForms();
    }

    @GetMapping("/userInfo/{id}")
    public UserModel getUserInfoById(@PathVariable String id) {
        return libraryService.getUserInfoById(Long.parseLong(id));
    }

    @PostMapping("/admin/addBook")
    public void postBook(@RequestBody FormModel formModel){
        libraryService.postBook(formModel);
    }

    @GetMapping("/admin/allReservations")
    public List<AllReservationsModel> getAllReservations() {
        return libraryService.getAllReservations();
    }

    @DeleteMapping("/deleteReservation/{reservationId}")
    public void deleteReservation(@PathVariable String reservationId){
        libraryService.deleteReservation(Long.parseLong(reservationId));
    }

    @GetMapping("/admin/allBorrowedBooks")
    public List<AllBorrowedBooksModel> getAllBorrowedBooks() {
        return libraryService.getAllBorrowedBooks();
    }

    @DeleteMapping("/deleteBorrowedBook/{borrowedBookId}")
    public void deleteBorrowedBook(@PathVariable String borrowedBookId){
        libraryService.deleteBorrowedBook(Long.parseLong(borrowedBookId));
    }

    @PostMapping("/admin/addBorrowedBook")
    public void postBorrowedBook(@RequestBody ReservationModel reservationModel){
        libraryService.postBorrowedBook(reservationModel);
    }



}
