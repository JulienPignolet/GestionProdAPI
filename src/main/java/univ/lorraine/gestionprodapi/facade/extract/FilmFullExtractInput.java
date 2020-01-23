package univ.lorraine.gestionprodapi.facade.extract;

import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

/**
 * Paramètres d'entrée pour l'extraction
 */
public class FilmFullExtractInput {
    private static final String FILE_NAME = "films"; // Nom du fichier
    private final String[] extractHeaders = {"Id", "Titre", "Résumé", "Date de sortie", "Titre original",
            "Langage original", "Popularité", "Nombre de votes", "Vote moyen", "Catégorie adulte"} ; // Titre des colonnes de l'extraction
    private final String[] extractColumns = {"getId", "getTitle", "getOverview", "getReleaseDate", "getOriginalTitle",
            "getOriginalLanguage", "getPopularity", "getVoteCount", "getVoteAverage", "isAdult"}; // Valeurs des colonnes de l'extraction (pour introspection)
    private String extension; // Extension du fichier
    private List<FilmEntity> filmList; // Liste des films

    public FilmFullExtractInput(String extension, List<FilmEntity> filmList) {
        this.extension = extension;
        this.filmList = filmList;
    }

    public String getFileName() {
        return FILE_NAME;
    }

    public String[] getExtractHeaders() {
        return extractHeaders;
    }

    public String[] getExtractColumns() {
        return extractColumns;
    }

    public String getExtension() {
        return extension;
    }

    public List<FilmEntity> getFilmList() {
        return filmList;
    }
}
