/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.controllers;

import com.sv.mastermind.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sv.mastermind.data.MastermindDao;
import com.sv.mastermind.model.Round;
import com.sv.mastermind.service.MastermindServiceLayer;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@RestController
@RequestMapping("/mastermind")
public class MastermindController {

//    private final MastermindDao dao;
    
//    public MastermindController(MastermindDao dao) {
//        this.dao = dao;
//    }
    
    private MastermindServiceLayer service;

    public MastermindController(MastermindServiceLayer service) {
        this.service = service;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        return service.createGame();
    }
    
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> playRound(@RequestBody Round round) {
        if (round == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(service.playRound(round));
    }
    
    @GetMapping("/game")
    public List<Game> getGamesList() {
        return service.getGamesList();
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable int gameId) {
        
        Game game = service.getSpecificGame(gameId);
        if (game.getBoard().length() != 4) {
            System.out.println("That game does not exist.");
            return new ResponseEntity(game, HttpStatus.NOT_FOUND);
        }
        if (game.getIsComplete() == false) {
            game.setBoard("GAME BOARD HIDDEN UNTIL GAME IS OVER");
        }
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRounds(@PathVariable int gameId) {
        
        List<Round> rounds = service.getRounds(gameId);

        return ResponseEntity.ok(rounds);
        
    }
}
