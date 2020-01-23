package univ.lorraine.gestionprodapi.facade.extract;

import org.springframework.web.servlet.ModelAndView;

/**
 * Extraction excel
 */
public class ExtractExcelExpert extends ExtractExpert {
    private final String EXTENSION = "xls";

    public ExtractExcelExpert(ExtractExpert nextExpert) {
        super(nextExpert);
    }

    @Override
    public ModelAndView extract(FilmFullExtractInput filmFullExtractInput) {
        if (filmFullExtractInput.getExtension().equals(EXTENSION)) {
            return new ModelAndView(new ExcelAdapter(), "extractInput", filmFullExtractInput);
        } else {
            return nextExpert != null ? nextExpert.extract(filmFullExtractInput) : null;
        }
    }
}
