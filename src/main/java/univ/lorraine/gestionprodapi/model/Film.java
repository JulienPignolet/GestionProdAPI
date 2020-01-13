package univ.lorraine.gestionprodapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@ApiModel(description = "Classe représentant un film")
@MappedSuperclass
public abstract class Film {
    @ApiModelProperty(notes = "Identifiant unique", example = "1", hidden = true)
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    protected Long id;

    @ApiModelProperty(notes = "Popularité", example = "565.278", position = 1)
    @Column(name = "Popularity")
    protected double popularity;

    @ApiModelProperty(notes = "Nombre de votes", example = "198", position = 2)
    @Column(name = "Vote_count")
    protected Long voteCount;

    @ApiModelProperty(notes = "Catégorie adulte", example = "false", position = 3)
    @Column(name = "adult")
    protected boolean adult;

    @ApiModelProperty(notes = "Langage original", example = "en", position = 4)
    @Column(name = "Original_language")
    protected String originalLanguage;

    @ApiModelProperty(notes = "Titre", example = "It Chapter Two", position = 5)
    @Column(name = "Title")
    protected String title;

    @ApiModelProperty(notes = "Titre original", example = "It Chapter Two", position = 6)
    @Column(name = "Original_title")
    protected String originalTitle;

    @ApiModelProperty(notes = "Vote moyen", example = "7.2", position = 7)
    @Column(name = "Vote_average")
    protected String voteAverage;

    @ApiModelProperty(notes = "Résumé", example = "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.", position = 8)
    @Lob // pour les string > 255 char
    @Column(name = "Overview")
    protected String overview;

    @ApiModelProperty(notes = "Date de sortie", example = "2019-09-06", position = 9)
    @Temporal(TemporalType.DATE)
    @Column(name = "Release_date")
    protected Date releaseDate;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
