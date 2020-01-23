package univ.lorraine.gestionprodapi.facade.extract;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import univ.lorraine.gestionprodapi.entity.FilmEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Adapter pour respect de l'architecture hexagonale
 */
public class ExcelAdapter extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        FilmFullExtractInput filmFullExtractInput = (FilmFullExtractInput) map.get("extractInput");
        httpServletResponse.setHeader("Content-Disposition",
                "attachment;filename=\"" + filmFullExtractInput.getFileName() + "." + filmFullExtractInput.getExtension() + "\"");
        Sheet sheet = workbook.createSheet("Film Data");
        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        int columnNum = 0;
        for (String headerName : filmFullExtractInput.getExtractHeaders()) {
            header.createCell(columnNum++).setCellValue(headerName);
        }
        for (FilmEntity film : filmFullExtractInput.getFilmList()) {
            columnNum = 0;
            Row row = sheet.createRow(rowNum++);
            for (String column : filmFullExtractInput.getExtractColumns()) {
                Method method = FilmEntity.class.getMethod(column);
                createCell(row, columnNum++, method.invoke(film));
            }
        }
    }

    /***
     * createCell de la librairie apachePOI ne prend pas en paramètre Object
     * Il faut donc convertir l'objet
     * @param row numéro de ligne
     * @param columnNum numéro de colonne
     * @param object objet à convertir
     */
    private void createCell(Row row, int columnNum, Object object) {
        if (object instanceof Long) {
            row.createCell(columnNum).setCellValue((Long)object);
        } else if (object instanceof Boolean) {
            row.createCell(columnNum).setCellValue((Boolean)object);
        } else if (object instanceof String) {
            row.createCell(columnNum).setCellValue((String)object);
        } else if (object instanceof Date) {
            row.createCell(columnNum).setCellValue((Date)object);
        } else if (object instanceof Double) {
            row.createCell(columnNum).setCellValue((Double)object);
        }
    }
}
