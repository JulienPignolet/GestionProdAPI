package univ.lorraine.GestionProdAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.lorraine.GestionProdAPI.dao.FilmDAO;
import univ.lorraine.GestionProdAPI.entity.Film;

import java.util.List;

@RestController
public class MovieRestController {
    private FilmDAO filmDAO;


    @Autowired
    public MovieRestController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Film>> findAllMovies(){
        return  ResponseEntity.ok(filmDAO.findAll());
    }

}
