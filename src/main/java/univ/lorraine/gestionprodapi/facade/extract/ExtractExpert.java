package univ.lorraine.gestionprodapi.facade.extract;

import org.springframework.web.servlet.ModelAndView;

/**
 * Chain of responsabilities pour l'extraction
 */
public abstract class ExtractExpert {
    protected ExtractExpert nextExpert;

    public ExtractExpert(ExtractExpert nextExpert) {
        this.nextExpert = nextExpert;
    }

    /**
     * Extraction propre à chaque format
     * @param filmFullExtractInput objet d'entrée pour l'extraction
     */
    public abstract ModelAndView extract(FilmFullExtractInput filmFullExtractInput);
}
