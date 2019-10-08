package univ.lorraine.GestionProdAPI;

import univ.lorraine.GestionProdAPI.entity.Film;
import univ.lorraine.GestionProdAPI.facade.FilmResearch;

import java.util.ArrayList;
import java.util.List;

public class FilmResearchTests {
    public static void main(String[] args) {
        Film f1 = new Film();
        f1.setTitle("Teton");
        Film f2 = new Film();
        f2.setTitle("tete");
        Film f3 = new Film();
        f3.setTitle("tacle");
        Film f4 = new Film();
        f4.setTitle("t eop");
        List<Film> fList = new ArrayList<>();
        fList.add(f4);
        fList.add(f1);
        fList.add(f2);
        fList.add(f3);
        FilmResearch fSearch = new FilmResearch();
        fList = fSearch.research(fList, "te");
        for (Film film : fList) {
            System.out.println(film.getTitle());
        }
    }
}
