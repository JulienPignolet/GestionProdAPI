package univ.lorraine.GestionProdAPI.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FILM")
public class Film {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Popularity")
    private double popularity;

    @Column(name = "Vote_count")
    private Long voteCount;

    @Column(name = "adult")
    private boolean adult;

    @Column(name = "Original_language")
    private String originalLanguage;

    @Column(name = "Title")
    private String title;

    @Column(name = "Original_title")
    private String originalTitle;

    @Column(name = "Vote_average")
    private String voteAverage;

    @Lob // pour les string > 255 char
    @Column(name = "Overview")
    private String overview;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return Double.parseDouble(voteAverage);
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
