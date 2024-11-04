package hh.sof03.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import hh.sof03.harjoitustyo.domain.AppUser;
import hh.sof03.harjoitustyo.domain.AppUserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@Controller
public class AppUserController {
    @Autowired
    private AppUserRepository repository;

    // RESTful service to get all users
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public List<AppUser> userListRest() {
        return (List<AppUser>) repository.findAll();
    }
    
    // RESTful service to get user by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{id}")
    public Optional<AppUser> getUserByIdRest(@PathVariable("id") Long userId) {
        return repository.findById(userId);
    }
}
