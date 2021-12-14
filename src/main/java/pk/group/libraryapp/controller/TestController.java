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


}
