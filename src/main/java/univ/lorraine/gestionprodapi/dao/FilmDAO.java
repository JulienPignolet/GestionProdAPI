package univ.lorraine.gestionprodapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univ.lorraine.gestionprodapi.entity.Film;

import java.util.List;

@Repository
public interface FilmDAO extends CrudRepository<Film, Long> {

    Film findOneById(Long id);

    List<Film> findAll();
}
