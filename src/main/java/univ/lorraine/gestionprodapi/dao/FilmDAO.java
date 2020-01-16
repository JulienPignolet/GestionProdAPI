package univ.lorraine.gestionprodapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

@Repository
public interface FilmDAO extends CrudRepository<FilmEntity, Long> {

    FilmEntity findOneById(Long id);

    List<FilmEntity> findAll();
}
