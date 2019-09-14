package univ.lorraine.GestionProdAPI.dao;

import org.springframework.data.repository.CrudRepository;
import univ.lorraine.GestionProdAPI.entity.Film;

import java.util.List;

public interface FilmDAO extends CrudRepository<Film, Long> {

    public List<Film> findByTitleLike(String name);

    public List<Film> findAll();
}
