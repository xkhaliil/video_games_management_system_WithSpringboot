package com.khalil.vgms.controllers;

import com.khalil.vgms.entities.Game;
import com.khalil.vgms.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @RequestMapping("showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("game", new Game());
        return "createGame";
    }

    @RequestMapping("/saveGame")
    public String saveGame(@Valid Game game, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            gameService.saveGame(game);
        }
        return "createGame";
    }

    @RequestMapping("/gamesList")
    public String gamesList(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        Page<Game> games = gameService.getAllGamesByPage(page, size);
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listGames";
    }

    @RequestMapping("/deleteGame")
    public String deleteGame(
            @RequestParam("gameId") Long gameId,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size
    ) {
        gameService.deleteGameById(gameId);
        Page<Game> games = gameService.getAllGamesByPage(page, size);
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listGames";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(
            @RequestParam("gameId") Long gameId,
            ModelMap modelMap
    ) {
        Game game = gameService.getGame(gameId);
        modelMap.addAttribute("game", game);
        return "updateGame";
    }

    @RequestMapping("/updateGame")
    public String updateGame(
            @ModelAttribute("game") Game game,
            @RequestParam("creationDate") String date,
            ModelMap modelMap
    ) throws ParseException {
        // Convert the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date creationDate = dateFormat.parse(String.valueOf(date));
        game.setDate(creationDate);
        gameService.updateGame(game);
        Page<Game> games = gameService.getAllGamesByPage(0, 2);
        modelMap.addAttribute("games", games);
        modelMap.addAttribute("pages", new int[games.getTotalPages()]);
        modelMap.addAttribute("currentPage", 0);
        return "listGames";
    }
}
