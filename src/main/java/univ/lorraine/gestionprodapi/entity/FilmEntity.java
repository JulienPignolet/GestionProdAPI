package univ.lorraine.gestionprodapi.entity;

import univ.lorraine.gestionprodapi.dto.FilmDTO;
import univ.lorraine.gestionprodapi.model.Film;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FILM")
public class FilmEntity  extends Film {
    public FilmEntity() {

    }

    public FilmEntity(FilmDTO filmDTO) {
        id = filmDTO.getId();
        voteCount = filmDTO.getVoteCount();
        adult = filmDTO.isAdult();
        originalLanguage = filmDTO.getOriginalLanguage();
        title = filmDTO.getTitle();
        originalTitle = filmDTO.getOriginalTitle();
        voteAverage = filmDTO.getVoteAverage();
        overview = filmDTO.getOverview();
        releaseDate = filmDTO.getReleaseDate();
        popularity = filmDTO.getPopularity();
    }
}
