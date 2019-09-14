package univ.lorraine.GestionProdAPI.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import univ.lorraine.GestionProdAPI.dao.FilmDAO;
import univ.lorraine.GestionProdAPI.entity.Film;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInit implements ApplicationRunner {

    private FilmDAO filmDAO;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        long count = filmDAO.count();

        if (count == 0) {
            Film f1 = new Film();

            f1.setTitle("John se balade en mer");

            Date d1 = df.parse("1980-12-20");
            f1.setRelease_date(d1);
            //
            Film f2 = new Film();

            f1.setTitle("John se balade en foret");

            Date d2 = df.parse("1990-11-27");
            f1.setRelease_date(d2);
            filmDAO.save(f1);
            filmDAO.save(f2);
        }
    }
}
