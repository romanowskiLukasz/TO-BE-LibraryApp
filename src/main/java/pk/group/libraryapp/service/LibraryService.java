package pk.group.libraryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pk.group.libraryapp.entities.*;
import pk.group.libraryapp.model.*;
import pk.group.libraryapp.repo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Service
public class LibraryService {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    BookRepo bookRepo;
    UserRepo userRepo;
    MessageRepo messageRepo;
    ReservationRepo reservationRepo;
    RatingRepo ratingRepo;
    FormRepo formRepo;
    BorrowedRepo borrowedRepo;
    PublishingHouseRepo publishingHouseRepo;
    BorrowedBooksRepo borrowedBooksRepo;

    @Autowired
    public LibraryService(BorrowedBooksRepo borrowedBooksRepo,BookRepo bookRepo,UserRepo userRepo,MessageRepo messageRepo,ReservationRepo reservationRepo,RatingRepo ratingRepo,FormRepo formRepo,BorrowedRepo borrowedRepo,PublishingHouseRepo publishingHouseRepo) {
        this.bookRepo=bookRepo;
        this.userRepo = userRepo;
        this.messageRepo=messageRepo;
        this.reservationRepo=reservationRepo;
        this.ratingRepo=ratingRepo;
        this.formRepo=formRepo;
        this.borrowedRepo=borrowedRepo;
        this.publishingHouseRepo=publishingHouseRepo;
        this.borrowedBooksRepo=borrowedBooksRepo;
    }

    public List<BookModel> getBooksInfo() {
        return bookRepo.getBooksInfo();
    }

    public List<PopularBookModel> getPopularBooksInfo() {
        return bookRepo.getPopularBooksInfo();
    }

    public List<UserModel> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public void registerUser(RegisterModel registerModel) {
        User user = User.builder()
                .name(registerModel.getName())
                .email(registerModel.getEmail())
                .password(PASSWORD_ENCODER.encode(registerModel.getPassword()))
                .build();
        userRepo.save(user);
    }

    public void addMessage(Messages messages) {
        Messages message = Messages.builder()
                .email(messages.getEmail())
                .topic(messages.getTopic())
                .contents(messages.getContents())
                .build();
        messageRepo.save(message);
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

    public BookModel getBookById(Long id){
        return bookRepo.getInfoById(id);
    }

    public int getBookAmount(Long id){
        return bookRepo.getById(id).getAmount();
    }

    public void updateBookAmount(Long id,Integer newAmount){
         bookRepo.getById(id).setAmount(newAmount);
    }

    public void addReservatio(Long bookId,Long userId) {

        Reservation reservation=Reservation.builder()
                .user(userRepo.getById(userId))
                .book(bookRepo.getById(bookId))
                .reservationDate( LocalDate.now())
                .returnDate(LocalDate.from(LocalDate.now().plusDays(7)))
                .build();

        reservationRepo.save(reservation);
    }

    public boolean reserveBook(ReservationModel reservationModel){
        int amount=getBookAmount(reservationModel.getBookId());
        if(amount>0){
            updateBookAmount(reservationModel.getBookId(),amount-1);
            addReservatio(reservationModel.getBookId(),reservationModel.getUserId());
            return true;
        }
        return false;

    }

    public UserModel getUserInfo(String email){
        return userRepo.findModelByEmail(email);
    }

    public List<UserReservationsModel> getReservations(Long id){
        return reservationRepo.getUserReservations(id);
    }

    public void addRating(RatingModel ratingModel){
        Rating rating=Rating.builder()
                .starsCount(ratingModel.getStars_count())
                .book(bookRepo.getById(ratingModel.getBook_book_id()))
                .user(userRepo.getById(ratingModel.getUser_id()))
                .build();
        ratingRepo.save(rating);

    }

    public List<RatingModel> getRatings(Long userId){
        return ratingRepo.getInfoById(userId);
    }



    public void PostForm(FormModel form){
        Form newForm=Form.builder()
                .description((form.getDescription()))
                .img(form.getImg())
                .author(form.getAuthor())
                .title(form.getTitle())
                .publishingHouse(form.getPublishingHouse())
                .user(userRepo.getById(form.getUserId()))
                .build();
        formRepo.save(newForm);
    }

    public String changeEmail(ChangeEmailModel changeEmailModel){

        User updatedUser= userRepo.getById(changeEmailModel.getId());
        if(PASSWORD_ENCODER.matches(changeEmailModel.getPassword(), updatedUser.getPassword())){
            updatedUser.setEmail(changeEmailModel.getNewEmail());
            userRepo.save(updatedUser);
            return "0";
        }
        else {
            return "1";
        }
    }

    public String changePassword(ChangePasswordModel changePasswordModel){
        User updatedUser= userRepo.getById(changePasswordModel.getId());
        if(PASSWORD_ENCODER.matches(changePasswordModel.getPassword(), updatedUser.getPassword())){
            updatedUser.setPassword(PASSWORD_ENCODER.encode(changePasswordModel.getNewPassword()));
            userRepo.save(updatedUser);
            return "0";
        }
        else {
            return "1";
        }

    }

    public List<UserBorrowModel> getBorrowedBooks(Long id){
        return borrowedRepo.getUserBorrowedBook(id);
    }

    public List<AvgRatingModel> getAvgRatings(){
        List<AvgRatingModel> avgBooksRatings=new ArrayList<>();
        for(long i=1;i<=bookRepo.count();i++) avgBooksRatings.add(ratingRepo.getAvgBookRating(i));
        return avgBooksRatings;
    }

    public void deleteForm(Long formId){
        formRepo.deleteById(formId);
    }

    public void deleteReservation(Long reservationId){
        reservationRepo.deleteById(reservationId);
    }


    public List<FormModel> getForms(){
        return formRepo.getAllForms();
    }

    public UserModel getUserInfoById(Long id){
        return userRepo.findUserById(id);
    }

    public void postBook(FormModel formModel){
        List<Author> bookAuthors=new ArrayList<>();
        Author newAuthor=Author.builder().name(formModel.getAuthor()).build();
        bookAuthors.add(newAuthor);

        PublishingHouse newPublishingHouse= PublishingHouse.builder().name(formModel.getPublishingHouse()).build();

        publishingHouseRepo.save(newPublishingHouse);

        Book newBook=Book.builder()
                .authors(bookAuthors)
                .img(formModel.getImg())
                .description(formModel.getDescription())
                .publishingHouse(newPublishingHouse)
                .title(formModel.getTitle())
                .build();
        bookRepo.save(newBook);
    }

    public void postBorrowedBook(ReservationModel reservationModel){
        BorrowedBooks newBorrowedBook=BorrowedBooks.builder()
                .user(userRepo.getById(reservationModel.getUserId()))
                .book(bookRepo.getById(reservationModel.getBookId()))
                .reservationDate( LocalDate.now())
                .returnDate(LocalDate.from(LocalDate.now().plusDays(7)))
                .build();
        borrowedBooksRepo.save(newBorrowedBook);
    }

    public List<AllReservationsModel> getAllReservations(){
        return reservationRepo.getAllReservations();
    }

}
