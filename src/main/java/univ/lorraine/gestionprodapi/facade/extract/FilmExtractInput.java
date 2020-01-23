package univ.lorraine.gestionprodapi.facade.extract;

import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.util.List;

public abstract class FilmExtractInput {
    private static final String FILE_NAME = "films"; // Nom du fichier
    private String extension;
    private List<FilmEntity> filmList;

    public FilmExtractInput(String extension, List<FilmEntity> filmList) {
        this.extension = extension;
        this.filmList = filmList;
    }

    public static String getFileName() {
        return FILE_NAME;
    }

    public String getExtension() {
        return extension;
    }

    public List<FilmEntity> getFilmList() {
        return filmList;
    }

    /**
     * Liste des titres à apposer dans l'extraction
     * @return Tableau avec les titres
     */
    public abstract String[] getExtractHeaders();

    /**
     * Liste des méthodes pour récupérer les données pour l'extraction
     * @return Tableau avec le nom des méthodes
     */
    public abstract String[] getExtractColumns();
}
