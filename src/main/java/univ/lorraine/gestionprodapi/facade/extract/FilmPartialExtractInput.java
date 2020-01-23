package univ.lorraine.gestionprodapi.facade.extract;

import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

public class FilmPartialExtractInput extends FilmExtractInput {
    public FilmPartialExtractInput(String extension, List<FilmEntity> filmList) {
        super(extension, filmList);
    }

    @Override
    public String[] getExtractHeaders() {
        String[] extractHeaders = {"Id", "Titre", "Résumé", "Date de sortie", "Vote moyen"};
        return extractHeaders;
    }

    @Override
    public String[] getExtractColumns() {
        String[] extractColumns = {"getId", "getTitle", "getOverview", "getReleaseDate", "getVoteAverage"};
        return extractColumns;
    }
}
