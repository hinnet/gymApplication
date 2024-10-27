package hh.sof03.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.sof03.harjoitustyo.domain.User;
import hh.sof03.harjoitustyo.domain.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@Controller
public class UserRestController {
    @Autowired
    private UserRepository repository;

    // RESTful service to get all users
    @GetMapping("/users")
    public List<User> userListRest() {
        return (List<User>) repository.findAll();
    }
    
    // RESTful service to get user by id
    @GetMapping("/user/{id}")
    public Optional<User> getUserByIdRest(@PathVariable("id") Long userId) {
        return repository.findById(userId);
    }
    
}
