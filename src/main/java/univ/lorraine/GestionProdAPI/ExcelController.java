package univ.lorraine.GestionProdAPI;

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
import univ.lorraine.GestionProdAPI.dao.FilmDAO;
import univ.lorraine.GestionProdAPI.entity.Film;
import univ.lorraine.GestionProdAPI.facade.ExcelReport;
import univ.lorraine.GestionProdAPI.facade.FilmResearch;

import java.util.List;

@Controller
@RequestMapping(("/excel"))
@Api(value = "Excel", description = "Extraction excel", tags = "Excel")
public class ExcelController {
    private final FilmDAO filmDAO;

    @Autowired
    public ExcelController(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @ApiOperation(value = "Extraction excel avec recherche")
    @GetMapping
    public ModelAndView getExcel(@ApiParam(value = "Paramètre optionnel. Si title n'est pas renseigné récupère l'ensemble des films, sinon lance une recherche sur les titres contenant cette expression") @RequestParam(value = "title", required = false) String exp) {
        List<Film> filmList = StringUtils.isBlank(exp) ? filmDAO.findAll() : FilmResearch.research(filmDAO.findAll(), exp);
        return new ModelAndView(new ExcelReport(), "filmList", filmList);
    }
}
