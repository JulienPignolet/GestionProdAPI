package univ.lorraine.gestionprodapi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import univ.lorraine.gestionprodapi.entity.Film;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerTest {

    @Autowired
    MovieRestController movieController;

    /**
     * Test récupération tous les films
     */
    @Test
    public void testMovies(){
        assertTrue(movieController.searchMovies("").getStatusCode() == HttpStatus.OK );
    }

    /**
     * Test récupération premier film de la liste
     */
    @Test
    public void testMovieById(){
        assertTrue(movieController.findOneMovie(2L).getStatusCode() == HttpStatus.OK );
    }

    /**
     * Test création d'un film
     */
    @Test
    public void testCreationMovie(){
        Film temp = createMovie();
        assertTrue(movieController.create(temp).getStatusCode() == HttpStatus.OK );
    }

    /**
     * Test delete film
     */
    @Test
    public void testDeleteMovieById(){
        assertTrue(movieController.findOneMovie(1L).getStatusCode() == HttpStatus.OK );
        movieController.delete(1L);
        assertTrue(movieController.findOneMovie(1L).getStatusCode() == HttpStatus.NOT_FOUND );
    }

    /**
     * Test récupération premier film de la liste
     */
    @Test
    public void testUpdateMovie(){
       Film temp = createMovie();
        assertTrue(movieController.put(2L,temp).getStatusCode() == HttpStatus.OK );
    }

    private Film createMovie(){
        Film temp = new Film();
        temp.setPopularity(565.278);
        temp.setAdult(false);
        temp.setVoteCount(198L);
        temp.setOriginalLanguage("en");
        temp.setTitle("it");
        temp.setOriginalTitle("it chapter 2");
        temp.setVoteAverage("7.2");
        temp.setReleaseDate(new Date());
        temp.setOverview("27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.");

        return temp;
    }
}
