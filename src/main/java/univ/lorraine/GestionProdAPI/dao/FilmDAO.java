package univ.lorraine.GestionProdAPI.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univ.lorraine.GestionProdAPI.entity.Film;

import java.util.List;

@Repository
public interface FilmDAO extends CrudRepository<Film, Long> {

    List<Film> findByTitleLike(String name);

    Film findOneById(Long id);

    List<Film> findAll();
}
