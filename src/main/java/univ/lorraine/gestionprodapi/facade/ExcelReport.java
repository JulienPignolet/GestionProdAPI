package univ.lorraine.gestionprodapi.facade;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import univ.lorraine.gestionprodapi.entity.FilmEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReport extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"films.xls\"");
        List<FilmEntity> filmList = (List<FilmEntity>) map.get("filmList");
        Sheet sheet = workbook.createSheet("Film Data");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id");
        header.createCell(1).setCellValue("Titre");
        header.createCell(2).setCellValue("Résumé");
        header.createCell(3).setCellValue("Date de sortie");
        header.createCell(4).setCellValue("Titre original");
        header.createCell(5).setCellValue("Langage original");
        header.createCell(6).setCellValue("Popularité");
        header.createCell(7).setCellValue("Nombre de votes");
        header.createCell(8).setCellValue("Vote moyen");
        header.createCell(9).setCellValue("Catégorie adulte");

        int rowNum = 1;
        for (FilmEntity film : filmList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(film.getId());
            row.createCell(1).setCellValue(film.getTitle());
            row.createCell(2).setCellValue(film.getOverview());
            row.createCell(3).setCellValue(film.getReleaseDate());
            row.createCell(4).setCellValue(film.getOriginalTitle());
            row.createCell(5).setCellValue(film.getOriginalLanguage());
            row.createCell(6).setCellValue(film.getPopularity());
            row.createCell(7).setCellValue(film.getVoteCount());
            row.createCell(8).setCellValue(film.getVoteAverage());
            row.createCell(9).setCellValue(film.isAdult());
        }
    }
}
