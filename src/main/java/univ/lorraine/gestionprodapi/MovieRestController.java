package univ.lorraine.gestionprodapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.lorraine.gestionprodapi.dao.FilmDAO;
import univ.lorraine.gestionprodapi.dto.FilmDTO;
import univ.lorraine.gestionprodapi.entity.FilmEntity;
import univ.lorraine.gestionprodapi.facade.FilmResearch;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(("/movies"))
@Api(value = "Films", description = "Accès aux films" ,tags = "Films")
public class MovieRestController {
    private final FilmDAO filmDAO;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MovieRestController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @ApiOperation(value = "Récupère l'ensemble des films spécifiés (selon si title est spécifié ou non)")
    @GetMapping
    public ResponseEntity<List<FilmEntity>> searchMovies(@ApiParam(value = "Paramètre optionnel. Si title n'est pas renseigné récupère l'ensemble des films, sinon lance une recherche sur les titres contenant cette expression") @RequestParam(value = "title", required = false) String exp) {
        if (StringUtils.isBlank(exp)) {
            return  ResponseEntity.ok(filmDAO.findAll());
        } else {
            return new ResponseEntity<>(FilmResearch.research(filmDAO.findAll(), exp), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Récupère le film grâce à son id si celui-ci existe")
    @GetMapping("/{id}")
    public ResponseEntity findOneMovie(@ApiParam(value = "id du film") @PathVariable Long id) {
        FilmEntity film = filmDAO.findOneById(id);

        return film != null ? ResponseEntity.ok(film) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Création d'un film")
    @PostMapping
    public ResponseEntity create(@ApiParam(value = "json du film") @Valid @RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(filmDAO.save(new FilmEntity(filmDTO)));
    }

    @ApiOperation(value = "Suppresion d'un film à l'aide de son id si celui-ci existe")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@ApiParam(value = "id du film") @PathVariable Long id){
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }
        filmDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Modification d'un film à l'aide de son id si celui-ci existe")
    @PutMapping("/{id}")
    public ResponseEntity put(@ApiParam(value = "id du film") @PathVariable Long id, @ApiParam(value = "json du film") @Valid @RequestBody FilmDTO filmDTO) {
        FilmEntity filmEntity = new FilmEntity(filmDTO);
        if (!findId(id)) {
            ResponseEntity.badRequest().build();
        }
        filmEntity.setId(id);
        return ResponseEntity.ok(filmDAO.save(filmEntity));
    }

    private boolean findId(Long id) {
        if (filmDAO.findById(id).isPresent()) {
            return true;
        } else {
            logger.error("Id {} is not existed",id);
            return false;
        }
    }


}
