package univ.lorraine.gestionprodapi.facade.extract;

import org.springframework.web.servlet.ModelAndView;

/**
 * Extraction excel
 */
public class ExtractExcelExpert extends ExtractExpert {
    public ExtractExcelExpert(ExtractExpert nextExpert) {
        super(nextExpert, "xls");
    }

    @Override
    public ModelAndView callAdapter(FilmExtractInput filmExtractInput) {
        return new ModelAndView(new ExcelAdapter(), "extractInput", filmExtractInput);
    }
}
