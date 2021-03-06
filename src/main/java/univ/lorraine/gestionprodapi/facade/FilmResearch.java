package univ.lorraine.gestionprodapi.facade;

import org.junit.platform.commons.util.StringUtils;
import univ.lorraine.gestionprodapi.entity.FilmEntity;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilmResearch {
    private FilmResearch() {

    }

    // Pattern pour enlever les adds du normalizer
    private static final Pattern DIACRITICS_AND_FRIENDS
            = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    /**
     * Méthode permettant de chercher tous les films contenant l'expression donnée
     * @param allFilms tous les films connus
     * @param exp expression à trouver
     * @return Liste de films
     */
    public static List<FilmEntity> research(List<FilmEntity> allFilms, String exp) {
        if (StringUtils.isBlank(exp) || allFilms == null || allFilms.isEmpty()) {
            return new ArrayList<>();
        }
        List<Map.Entry<FilmEntity, Integer>> filmByScore = new ArrayList<>();
        for (FilmEntity film : allFilms) {
             int score = evalScore(film.getTitle(), exp);
             if (score != -2) {
                 filmByScore.add(new AbstractMap.SimpleImmutableEntry<>(film, score));
             }
        }
        filmByScore.sort(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));
        return filmByScore.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Méthode donnant le score d'une film donnée pour la recherche d'une expression
     * @param filmTitle titre d"un film
     * @param exp expression donnée
     * @return score du film
     */
    public static int evalScore(String filmTitle, String exp) {
        int max = -2;
        if (StringUtils.isBlank(filmTitle) || StringUtils.isBlank(exp)) {
            return -2;
        }
        while (filmTitle.length() >= exp.length()) {
            int compare = compareTwoWords(filmTitle, exp, 0);
            max = Math.max(compare, max);
            filmTitle = filmTitle.substring(1);
        }
        return max;
    }

    /**
     * Méthode récursive comparant si l'expression donnée est comprise dans le début du titre donné
     * @param filmTitle titre d'un film
     * @param exp expression recherchée
     * @param score score de la recherche
     * @return score de la recherche
     */
    public static int compareTwoWords(String filmTitle, String exp, int score) {
        if ((StringUtils.isBlank(filmTitle) || StringUtils.isBlank(exp)) && score == 0) {
            return -2;
        }
        if (exp.length() == 0) {
            return score;
        } else {
            int compare = compareTwoLetter(filmTitle.charAt(0), exp.charAt(0));
            return  compare == -2 ? -2 : compareTwoWords(filmTitle.substring(1), exp.substring(isSpace(compare)), score + compare);
        }
    }

    /**
     * Méthode comparant deux lettres
     * @param filmLetter lettre d'un titre de film
     * @param expLetter lettre de l'expression recherchée
     * @return score :
     * -2 = ne correspond pas
     * -1 = espace
     * 1 = identique mais accentué majuscule
     * 2 = identique mais accentué
     * 3 = identique mais majuscule
     * 4 = identique
     */
    public static int compareTwoLetter(char filmLetter, char expLetter) {
        if (filmLetter == ' ') {
            return -1;
        } else if (expLetter == filmLetter) {
            return 4;
        } else if (expLetter == Character.toLowerCase(filmLetter)) {
            return 3;
        } else if (expLetter == stripDiacritics(filmLetter+"").charAt(0)) {
            return 2;
        } else if (expLetter == stripDiacritics(filmLetter+"").toLowerCase().charAt(0))  {
            return 1;
        }
        else {
            return -2;
        }
    }

    /**
     * Méthode normalisant les caractères accentués
     * @param str chaîne à normaliser
     * @return chaîne normalisée
     */
    public static String stripDiacritics(String str) {
        if (!StringUtils.isBlank(str)) {
            str = Normalizer.normalize(str, Normalizer.Form.NFD);
            str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
        }
        return str;
    }

    /**
     * @param compare permet de savoir si le dernier caractère est un espace
     * @return 0 si compare = espace, 1 sinon
     */
    private static int isSpace(int compare) {
        return compare == -1 ? 0 : 1;
    }
}
