package univ.lorraine.gestionprodapi.facade.extract;

import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

public class FilmPartialExtractInput extends FilmExtractInput {
    public FilmPartialExtractInput(String extension, List<FilmEntity> filmList) {
        super(extension, filmList);
    }

    @Override
    public String[] getExtractHeaders() {
        return new String[] {"Id", "Titre", "Résumé", "Date de sortie", "Vote moyen"};
    }

    @Override
    public String[] getExtractColumns() {
        return new String[] {"getId", "getTitle", "getOverview", "getReleaseDate", "getVoteAverage"};
    }
}
