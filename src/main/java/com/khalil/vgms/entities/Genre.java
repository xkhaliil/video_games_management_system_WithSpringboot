package com.khalil.vgms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GenreId;
    private String GenreName;


    public Genre(String genreName) {
        GenreName = genreName;

    }

    public Genre() {
    }
}
