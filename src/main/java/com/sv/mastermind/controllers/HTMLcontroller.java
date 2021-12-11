/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.controllers;

import com.sv.mastermind.data.MastermindDao;
import com.sv.mastermind.model.Game;
import com.sv.mastermind.model.Round;
import com.sv.mastermind.service.MastermindServiceLayer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Controller
public class HTMLcontroller {
    
    @Autowired
    MastermindDao dao;
    
    @Autowired
    MastermindServiceLayer service;
    
    @GetMapping("/games")
    public String displayGames(Model model) {
        List<Game> games = dao.getAllGames();
        games.stream().forEach(game -> {
             if (!game.getIsComplete()) {
                 game.setBoard("XXXX");
             }
        });
        for (Game game : games) {
            int rounds = dao.gameRounds(game.getGameId()).size();
            
            String ranking = "Ugh! You need practice.";
            if (rounds < 11) ranking = "a MASTERBRAIN!";
            else if (rounds < 16) ranking = "a Master!";
            else if (rounds < 20) ranking = "an OK player.";
            
            if (game.getIsComplete()) {
                game.setInfo(rounds + " rounds to finish: You are... " + ranking);
            } else {
                game.setInfo(rounds + " round" + (rounds == 1 ? " " : "s ") + "played so far.");
            }
        }
        model.addAttribute("games", games);
        return "games";
    }
    
    @GetMapping("/playgame")
    public String playGame(Integer id, Model model) {
        int gameId = id;
        System.out.println(gameId);
        
        Game game = null;
                
        if (gameId == 0) {
            game = service.createGame();
        } else {
            game = service.getSpecificGame(gameId);
        }
        
        List<Round> rounds = service.getRounds(gameId);
        
        model.addAttribute("game", game);
        model.addAttribute("rounds", rounds);
        
        return "play";
    }
    
    @PostMapping("playround")
    public String playRound(Model model, String guess, Integer gameId) {
        
        Round newRound = new Round();
        newRound.setGuess(guess);
        newRound.setGameId(gameId);
        service.playRound(newRound);
        
        List<Round> rounds = service.getRounds(gameId);
        Game game = service.getSpecificGame(gameId);
        
        model.addAttribute("rounds", rounds);
        model.addAttribute("game", game);
        
        return "play";
        
        
        
        
    }
    
    

}
