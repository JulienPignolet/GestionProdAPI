package univ.lorraine.gestionprodapi.facade.extract;

import org.springframework.web.servlet.ModelAndView;

/**
 * Chain of responsabilities pour l'extraction
 */
public abstract class ExtractExpert {
    protected ExtractExpert nextExpert;
    private final String extension;

    public ExtractExpert(ExtractExpert nextExpert, String extension) {
        this.nextExpert = nextExpert;
        this.extension = extension;
    }

    /**
     * Extraction propre à chaque format
     * @param filmExtractInput objet d'entrée pour l'extraction
     */
    public ModelAndView extract(FilmExtractInput filmExtractInput) {
        if (filmExtractInput.getExtension().equals(extension)) {
            return callAdapter(filmExtractInput);
        } else {
            return nextExpert != null ? nextExpert.extract(filmExtractInput) : null;
        }
    }

    /**
     * Adapter spécifique à chaque expert/format
     * @param filmExtractInput objet d'entrée pour l'extraction
     */
    public abstract  ModelAndView callAdapter(FilmExtractInput filmExtractInput);
}
