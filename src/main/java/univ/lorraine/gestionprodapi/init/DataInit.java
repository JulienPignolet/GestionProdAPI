package univ.lorraine.gestionprodapi.init;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import univ.lorraine.gestionprodapi.dao.FilmDAO;
import univ.lorraine.gestionprodapi.entity.Film;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataInit implements ApplicationRunner {

    private final FilmDAO filmDAO;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DataInit(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        
        try (
                InputStream initialStream =  getClass().getClassLoader().getResourceAsStream("datas.json");
                Reader reader = new InputStreamReader(initialStream);
        ) {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            JSONArray filmList = (JSONArray) obj.get("results");

            //Iterate over film array
            filmList.forEach(film -> parseFilmObject((JSONObject) film));

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void parseFilmObject(JSONObject film) {
        Film temp = new Film();

        temp.setTitle((String) film.get("title"));
        temp.setVoteCount((Long) film.get("vote_count"));
        temp.setAdult((Boolean) film.get("adult"));
        temp.setOriginalLanguage((String) film.get("original_language"));
        temp.setOriginalTitle((String) film.get("original_title"));
        temp.setPopularity((Double) film.get("popularity"));

        temp.setVoteAverage(film.get("vote_average").toString());
        temp.setOverview((String) film.get("overview"));
        try {
            temp.setReleaseDate(df.parse((String) film.get("release_date")));
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }

        filmDAO.save(temp);
    }
}
