package univ.lorraine.gestionprodapi.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

public class FilmDTO {
    @ApiModelProperty(notes = "Identifiant unique", example = "1", hidden = true)
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @ApiModelProperty(notes = "Popularité", example = "565.278", position = 1)
    @Column(name = "Popularity")
    private double popularity;

    @ApiModelProperty(notes = "Nombre de votes", example = "198", position = 2)
    @Column(name = "Vote_count")
    private Long voteCount;

    @ApiModelProperty(notes = "Catégorie adulte", example = "false", position = 3)
    @Column(name = "adult")
    private boolean adult;

    @ApiModelProperty(notes = "Langage original", example = "en", position = 4)
    @Column(name = "Original_language")
    private String originalLanguage;

    @ApiModelProperty(notes = "Titre", example = "It Chapter Two", position = 5)
    @Column(name = "Title")
    private String title;

    @ApiModelProperty(notes = "Titre original", example = "It Chapter Two", position = 6)
    @Column(name = "Original_title")
    private String originalTitle;

    @ApiModelProperty(notes = "Vote moyen", example = "7.2", position = 7)
    @Column(name = "Vote_average")
    private String voteAverage;

    @ApiModelProperty(notes = "Résumé", example = "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.", position = 8)
    @Lob // pour les string > 255 char
    @Column(name = "Overview")
    private String overview;

    @ApiModelProperty(notes = "Date de sortie", example = "2019-09-06", position = 9)
    @Temporal(TemporalType.DATE)
    @Column(name = "Release_date")
    private Date releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
