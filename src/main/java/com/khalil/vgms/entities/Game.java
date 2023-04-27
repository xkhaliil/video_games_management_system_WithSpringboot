package com.khalil.vgms.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 35)
    private String Name;
    @NotNull
    private String Publisher;
    private String Rating;
    @Min(value = 10)
    @Max(value = 1500)
    private Double Price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date Date;
    @ManyToOne
    @JoinColumn(name = "GenreId")
    private Genre genre;

    public Game(String gameName, String gamePublisher, String gameDescription, Double gamePrice, Date gameReleaseDate, Genre genre) {
        Name = gameName;
        Publisher = gamePublisher;
        Rating = gameDescription;
        Price = gamePrice;
        Date = gameReleaseDate;
        this.genre = genre;
    }

    public Game() {
    }
}
