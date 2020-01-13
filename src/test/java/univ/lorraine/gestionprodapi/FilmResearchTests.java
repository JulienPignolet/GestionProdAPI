package univ.lorraine.gestionprodapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import univ.lorraine.gestionprodapi.entity.FilmEntity;
import univ.lorraine.gestionprodapi.facade.FilmResearch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class FilmResearchTests {
    @ParameterizedTest
    @CsvSource({"Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ,This is a funky String,[Test Suppression des accents]",
            "Une phrase sans accents,Une phrase sans accents,[Test Phrase sans accents]",
            ",,[Test Null]",
            "'','',[Test Empty]",
            "'            ','            ',[Test Whitespace]"})
    void stripDiacriticsTest(String testString, String expected, String message) {
        assertEquals(message, expected, FilmResearch.stripDiacritics(testString));
    }

    @ParameterizedTest
    @CsvSource({"' ',a,-1,[Test espace]",
            "a,a,4,[Test identique]",
            "A,a,3,[Test identique mais majuscule]",
            "é,e,2,[Test identique mais accent]",
            "É,e,1,[Test identique mais accent et majuscule]",
            "a,b,-2,[Test différent]"
            })
    void compareTwoLetterTest(char testLetter1, char testLetter2, int expected, String message) {
        assertEquals(message, expected, FilmResearch.compareTwoLetter(testLetter1, testLetter2));
    }

    @ParameterizedTest
    @CsvSource({"tea,te,8,[Test trouvé]",
            "tea,tea,12,[Test trouvé]",
            "ate,te,-2,[Test non trouvé]",
            "blo,te,-2,[Test non trouvé]",
            ",,-2,[Test null]",
            "'','',-2,[Test empty]",
            "'   ','   ',-2,[Test whitespace]"})
    void compareTwoWordsTest(String testFilmTitle, String testExp, int expected, String message) {
        assertEquals(message, expected, FilmResearch.compareTwoWords(testFilmTitle, testExp, 0));
    }

    @ParameterizedTest
    @CsvSource({"tea,tea,12,[Test trouvé]",
            "thetea,tea,12,[Test trouvé]",
            "l amende,lame,15,[Test identique mais espace]",
            "thé,the,10,[Test identique mais accent]",
            "THE PLAYER,the,9,[Test identique mais majuscule]",
            "THÉ GLACÉ,the,7,[Test identique mais majuscule et accentué]",
            "t    hOP,th,4,[Test multiples espaces]",
            "t,tea,-2,[Test non trouvé]",
            "'','',-2,[Test empty]",
            "'   ','   ',-2,[Test whitespace]",
            ",,-2,[Test null]"})
    void evalScoreTest(String testFilmTitle, String testExp, int expected, String message) {
        assertEquals(message, expected, FilmResearch.evalScore(testFilmTitle, testExp));
    }

    @Test
    void researchTest() {
        FilmEntity testFilm1 = new FilmEntity();
        testFilm1.setTitle("toto");
        FilmEntity testFilm2 = new FilmEntity();
        testFilm2.setTitle("tete");
        FilmEntity testFilm3 = new FilmEntity();
        testFilm3.setTitle("Tete");
        FilmEntity testFilm4 = new FilmEntity();
        testFilm4.setTitle("T    ëton");
        FilmEntity testFilm5 = new FilmEntity();
        testFilm5.setTitle("Pirouett  e");
        List<FilmEntity> testFilmList = new ArrayList<>();
        testFilmList.add(testFilm1);
        testFilmList.add(testFilm2);
        testFilmList.add(testFilm3);
        testFilmList.add(testFilm4);
        testFilmList.add(testFilm5);
        testFilmList = FilmResearch.research(testFilmList, "te");
        assertEquals(testFilm2.getTitle(), testFilmList.get(0).getTitle());
        assertEquals(testFilm3.getTitle(), testFilmList.get(1).getTitle());
        assertEquals(testFilm5.getTitle(), testFilmList.get(2).getTitle());
        assertEquals(testFilm4.getTitle(), testFilmList.get(3).getTitle());
    }

    @Test
    void researchTestBadCases() {
        assertTrue(FilmResearch.research(null, "ok").isEmpty());
        assertTrue(FilmResearch.research(new ArrayList<>(), "ok").isEmpty());
        assertTrue(FilmResearch.research(new ArrayList<>(), "").isEmpty());
        assertTrue(FilmResearch.research(new ArrayList<>(), null).isEmpty());
        assertTrue(FilmResearch.research(new ArrayList<>(), "   ").isEmpty());
    }
}
