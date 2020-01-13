package univ.lorraine.gestionprodapi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import univ.lorraine.gestionprodapi.dto.FilmDTO;
import univ.lorraine.gestionprodapi.model.Film;

import javax.persistence.*;
import java.util.Date;

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
    }
}
