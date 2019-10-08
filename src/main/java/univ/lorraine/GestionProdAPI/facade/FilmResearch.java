package univ.lorraine.GestionProdAPI.facade;

import univ.lorraine.GestionProdAPI.entity.Film;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilmResearch {
    // Pattern pour enlever les adds du normalizer
    public static final Pattern DIACRITICS_AND_FRIENDS
            = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    /**
     * Méthode permettant de chercher tous les films contenant l'expression donnée
     * @param allFilms tous les films connus
     * @param exp expression à trouver
     * @return Liste de films
     */
    public List<Film> research(List<Film> allFilms, String exp) {
        List<Map.Entry<Film, Integer>> filmByScore = new ArrayList<>();
        for (Film film : allFilms) {
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
    public int evalScore(String filmTitle, String exp) {
        int max = -2;
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
    public int compareTwoWords(String filmTitle, String exp, int score) {
        if (exp.length() == 0) {
            return score;
        } else {
            int compare = compareTwoLetter(filmTitle.charAt(0), exp.charAt(0));
            return  compare == -2 ? -2 : compareTwoWords(filmTitle.substring(1), exp.substring(compare == -1 ? 0 : 1), score + compare);
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
    public int compareTwoLetter(char filmLetter, char expLetter) {
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
    public String stripDiacritics(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
        return str;
    }
}
