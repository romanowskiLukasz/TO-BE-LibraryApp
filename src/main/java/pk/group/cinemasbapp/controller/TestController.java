package pk.group.cinemasbapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import pk.group.cinemasbapp.model.User;
import pk.group.cinemasbapp.repo.UserRepo;

@RestController
public class TestController {

    UserRepo userRepo;

    @Autowired
    public TestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        User user = new User("Piotr", "Migda", "piotr16@fake.pl", "lubieAGH123");

        userRepo.save(user);
    }
}
