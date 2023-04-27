package com.khalil.vgms.repos;

import com.khalil.vgms.entities.Game;
import com.khalil.vgms.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT a FROM Game a WHERE a.Name LIKE %:Name AND a.Price > :Price")
    List<Game> findByNamePrice(@Param("Name") String Name, @Param("Price") Double Price);

    @Query("SELECT a from Game  a where a.genre = ?1")
    List<Game> findByGenre(Genre genre);

    @Query("select a from Game a order by a.Name ASC, a.Price DESC ")
    List<Game> trierGamesNomsPrix();
}
