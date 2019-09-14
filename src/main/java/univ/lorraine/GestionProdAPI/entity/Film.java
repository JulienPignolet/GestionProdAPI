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
    private int voteCount;

    @Column(name = "adult")
    private boolean adult;

    @Column(name = "Original_language")
    private String originalLanguage;

    @Column(name = "Title")
    private String title;

    @Column(name = "Vote_average")
    private double vote_average;

    @Column(name = "Overview")
    private String overview;

    @Temporal(TemporalType.DATE)
    @Column(name = "Release_date")
    private Date release_date;

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

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
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

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
}
