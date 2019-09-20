package univ.lorraine.GestionProdAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private final FilmDAO filmDAO;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public MovieRestController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @ApiOperation(value = "Récupère l'ensemble des films disponibles")
    @GetMapping
    public ResponseEntity<List<Film>> findAllMovies(){
        return  ResponseEntity.ok(filmDAO.findAll());
    }

    @ApiOperation(value = "Récupère le film grâce à son id si celui-ci existe")
    @GetMapping("/{id}")
    public ResponseEntity<Film> findOneMovie(@PathVariable Long id) { return ResponseEntity.ok(filmDAO.findOneById(id)); }

    /**
     * Exemple de JSON
     * {
     "popularity": 54.766,
     "voteCount": 2080,
     "adult": false,
     "originalLanguage": "en",
     "originalTitle": "Toy Stary GI",
     "title": "Toy Story 4",
     "voteAverage": 7.7,
     "overview": "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
     "releaseDate": "2019-06-21"
     }
     */
    @ApiOperation(value = "Création d'un film")
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Film film) {
        return ResponseEntity.ok(filmDAO.save(film));
    }

    @ApiOperation(value = "Suprresion d'un film à l'aide de son id si celui-ci existe")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }
        filmDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Modification d'un film à l'aide de son id si celui-ci existe")
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @Valid @RequestBody Film film) {
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }

        film.setId(id);

        return ResponseEntity.ok(filmDAO.save(film));
    }

    private boolean findId(Long id) {
        if (filmDAO.findById(id).isPresent()) {
            return true;
        } else {
            logger.error("Id " + id + " is not existed");
            return false;
        }
    }
}
