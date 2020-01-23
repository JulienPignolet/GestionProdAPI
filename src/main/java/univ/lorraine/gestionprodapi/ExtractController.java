package univ.lorraine.gestionprodapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import univ.lorraine.gestionprodapi.dao.FilmDAO;
import univ.lorraine.gestionprodapi.entity.FilmEntity;
import univ.lorraine.gestionprodapi.facade.FilmResearch;
import univ.lorraine.gestionprodapi.facade.extract.ExtractExcelExpert;
import univ.lorraine.gestionprodapi.facade.extract.ExtractExpert;
import univ.lorraine.gestionprodapi.facade.extract.FilmFullExtractInput;
import univ.lorraine.gestionprodapi.facade.extract.FilmPartialExtractInput;

import java.util.List;

@Controller
@RequestMapping(("/extract"))
@Api(value = "Extraction", description = "Export des films", tags = "Extraction")
public class ExtractController {
    private final FilmDAO filmDAO;
    private final ExtractExpert extractExpert;



    @Autowired
    public ExtractController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
        extractExpert = new ExtractExcelExpert(null);
    }

    @ApiOperation(value = "Extraction excel avec recherche de tous les champs")
    @GetMapping(value = "/excel/full")
    public ModelAndView getFullExcel(@ApiParam(value = "Paramètre optionnel. Si title n'est pas renseigné récupère l'ensemble des films, sinon lance une recherche sur les titres contenant cette expression") @RequestParam(value = "title", required = false) String exp) {
        List<FilmEntity> filmList = StringUtils.isBlank(exp) ? filmDAO.findAll() : FilmResearch.research(filmDAO.findAll(), exp);
        return extractExpert.extract(new FilmFullExtractInput("xls", filmList));
    }

    @ApiOperation(value = "Extraction excel avec recherche d'un nombre limités de champs")
    @GetMapping(value = "excel/partial")
    public ModelAndView getPartialExcel(@ApiParam(value = "Paramètre optionnel. Si title n'est pas renseigné récupère l'ensemble des films, sinon lance une recherche sur les titres contenant cette expression") @RequestParam(value = "title", required = false) String exp) {
        List<FilmEntity> filmList = StringUtils.isBlank(exp) ? filmDAO.findAll() : FilmResearch.research(filmDAO.findAll(), exp);
        return extractExpert.extract(new FilmPartialExtractInput("xls", filmList));
    }
}
