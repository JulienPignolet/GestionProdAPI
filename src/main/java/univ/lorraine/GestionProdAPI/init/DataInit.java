package univ.lorraine.GestionProdAPI.init;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import univ.lorraine.GestionProdAPI.dao.FilmDAO;
import univ.lorraine.GestionProdAPI.entity.Film;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataInit implements ApplicationRunner {

    private final FilmDAO filmDAO;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(new ClassPathResource(
                "datas.json").getFile()))
        {
            //Read JSON file
            JSONObject  obj = (JSONObject) jsonParser.parse(reader);

            JSONArray filmList = (JSONArray) obj.get("results");

            //Iterate over film array
            filmList.forEach( film -> parseFilmObject( (JSONObject) film ) );

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseFilmObject(JSONObject film) {
        Film temp = new Film();

        temp.setTitle((String)film.get("title"));
        temp.setVoteCount((Long)film.get("vote_count"));
        temp.setAdult((Boolean) film.get("adult"));
        temp.setOriginalLanguage((String) film.get("original_language"));
        temp.setOriginalTitle((String) film.get("original_title"));
        temp.setPopularity((Double) film.get("popularity"));
        System.out.println(film);
        temp.setVoteAverage(film.get("vote_average").toString());
        temp.setOverview((String) film.get("overview"));
        try {
            temp.setReleaseDate( df.parse((String)film.get("release_date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        filmDAO.save(temp);
    }
}
