package hh.sof03.harjoitustyo.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof03.harjoitustyo.domain.AppUser;
import hh.sof03.harjoitustyo.domain.AppUserRepository;

// Spring Security käyttää tätä luokkaa tunnistaakseen ja valtuuttaakseen käyttäjän
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;

    public UserDetailServiceImpl(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentUser = repository.findByUsername(username);
        UserDetails user = new User(username, currentUser.getPassword(),
        AuthorityUtils.createAuthorityList(currentUser.getRole()));

        return user;
    }
}
