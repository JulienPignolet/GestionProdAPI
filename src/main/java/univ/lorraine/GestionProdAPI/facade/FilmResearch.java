package univ.lorraine.GestionProdAPI.facade;

import univ.lorraine.GestionProdAPI.entity.Film;

import java.util.*;
import java.util.stream.Collectors;

public class FilmResearch {
    public List<Film> research(List<Film> allFilms, String exp) {
        List<Map.Entry<Film, Integer>> filmByScore = new ArrayList<>();
        for (Film film : allFilms) {
             int score = evalScore(film.getTitle(), exp);
             if (score != -2) {
                 filmByScore.add(new AbstractMap.SimpleImmutableEntry<>(film, score));
             }
        }
        Collections.sort(filmByScore, Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));
        return filmByScore.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public int evalScore(String filmTitle, String exp) {
        int max = -2;
        while (filmTitle.length() >= exp.length()) {
            int compare = compareTwoWords(filmTitle, exp, 0);
            max = Math.max(compare, max);
            filmTitle = filmTitle.substring(1);
        }
        return max;
    }

    public int compareTwoWords(String filmTitle, String exp, int score) {
        if (exp.length() == 0) {
            return score;
        } else {
            int compare = compareTwoLetter(filmTitle.charAt(0), exp.charAt(0));
            return  compare == -2 ? -2 : compareTwoWords(filmTitle.substring(1), exp.substring(compare == -1 ? 0 : 1), score + compare);
        }
    }

    public int compareTwoLetter(char a, char b) {
        if (a == ' ') {
            return -1;
        } else if (a == b) {
            return 4;
        } else if (Character.toLowerCase(a) == Character.toLowerCase(b)) {
            return 3;
        } else {
            return -2;
        }
    }
}
