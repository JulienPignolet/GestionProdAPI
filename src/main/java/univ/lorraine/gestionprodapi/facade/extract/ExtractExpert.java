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
     * @param filmFullExtractInput objet d'entrée pour l'extraction
     */
    public ModelAndView extract(FilmFullExtractInput filmFullExtractInput) {
        if (filmFullExtractInput.getExtension().equals(extension)) {
            return callAdapter(filmFullExtractInput);
        } else {
            return nextExpert != null ? nextExpert.extract(filmFullExtractInput) : null;
        }
    }

    /**
     * Adapter spécifique à chaque expert/format
     * @param filmFullExtractInput objet d'entrée pour l'extraction
     */
    public abstract  ModelAndView callAdapter(FilmFullExtractInput filmFullExtractInput);
}
