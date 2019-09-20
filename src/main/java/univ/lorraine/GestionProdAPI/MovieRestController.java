package univ.lorraine.GestionProdAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.lorraine.GestionProdAPI.dao.FilmDAO;
import univ.lorraine.GestionProdAPI.entity.Film;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(("/movies"))
public class MovieRestController {
    private FilmDAO filmDAO;

    @Autowired
    public MovieRestController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping
    public ResponseEntity<List<Film>> findAllMovies(){
        return  ResponseEntity.ok(filmDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> findOneMovie(@PathVariable Long id) { return ResponseEntity.ok(filmDAO.findOneById(id)); }

    /**
     * Exemple de JSON
     * {
     "popularity": 54.766,
     "vote_count": 2080,
     "adult": false,
     "original_language": "en",
     "original_title": "Toy Stary GI",
     "title": "Toy Story 4",
     "vote_average": 7.7,
     "overview": "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
     "release_date": "2019-06-21"
     }
     */
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(filmDAO.save(film));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }
        filmDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @Valid @RequestBody Film film) {
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }
        Film oldFilm = filmDAO.findOneById(id);
        oldFilm.setPopularity(film.getPopularity());
        oldFilm.setVoteCount(film.getVoteCount());
        oldFilm.setAdult(film.isAdult());
        oldFilm.setOriginalLanguage(film.getOriginalLanguage());
        oldFilm.setOriginalTitle(film.getOriginalTitle());
        oldFilm.setTitle(film.getTitle());
        oldFilm.setVoteAverage(film.getVoteAverage());
        oldFilm.setOverview(film.getOverview());
        oldFilm.setReleaseDate(film.getReleaseDate());
        return ResponseEntity.ok(filmDAO.save(oldFilm));
    }

    private boolean findId(Long id) {
        if (filmDAO.findById(id).isPresent()) {
            return true;
        } else {
            System.err.println("Id " + id + " is not existed");
            return false;
        }
    }
}
