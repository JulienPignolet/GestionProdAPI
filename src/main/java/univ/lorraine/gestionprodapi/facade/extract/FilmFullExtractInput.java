package univ.lorraine.gestionprodapi.facade.extract;

import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

/**
 * Paramètres d'entrée pour l'extraction
 */
public class FilmFullExtractInput extends FilmExtractInput {
    public FilmFullExtractInput(String extension, List<FilmEntity> filmList) {
        super(extension, filmList);
    }

    @Override
    public String[] getExtractHeaders() {
        return new String[] {"Id", "Titre", "Résumé", "Date de sortie", "Titre original",
                "Langage original", "Popularité", "Nombre de votes", "Vote moyen", "Catégorie adulte"};
     }

    @Override
    public String[] getExtractColumns() {

        return new String[] {"getId", "getTitle", "getOverview", "getReleaseDate", "getOriginalTitle",
                "getOriginalLanguage", "getPopularity", "getVoteCount", "getVoteAverage", "isAdult"};
    }
}
