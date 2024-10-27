package hh.sof03.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);
    // TODO: poista 
    // Jos sovelluksessa on esimerkiksi toiminnallisuus, 
    // jossa käyttäjän tulee kirjautua sisään antamalla sähköpostiosoitteensa, 
    // sinun täytyy hakea tietokannasta käyttäjätiedot, 
    // jotka vastaavat annettua sähköpostiosoitetta. 
    // Tällöin käytetään repository-metodia, 
    // joka osaa hakea tietoja sähköpostikentän perusteella.
    // EHTOLAUSE: User user = userService.getUserByEmail(user@gmail.com); if (user != null){...} else{...})
}
