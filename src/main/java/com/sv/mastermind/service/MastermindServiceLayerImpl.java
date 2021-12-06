/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.service;

import com.sv.mastermind.data.MastermindDao;
import com.sv.mastermind.model.Game;
import com.sv.mastermind.model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Service
public class MastermindServiceLayerImpl implements MastermindServiceLayer {

    MastermindDao dao;
    
    @Autowired
    public MastermindServiceLayerImpl(MastermindDao dao) {
        this.dao = dao;
    }
    
    Random rnd = new Random();
    
    @Override
    public Game createGame() {
        String board = "";
        while (board.length() < 4) {
            int randomNumber = rnd.nextInt(10);
            String randomString = String.valueOf(randomNumber);
            if (!board.contains(randomString)) {
                board = board + randomString;
            }
        }
        
        Game game = new Game();
        game.setBoard(board);
        dao.newGame(game);
        
        return game;
    }

    @Override
    public Round playRound(Round round) {
        
        int partialMatches = 0;
        int exactMatches = 0;
        
        int gameId = round.getGameId();
        Game game = getSpecificGame(gameId);
        
        
        // check to see if player chose an already finished game
        // and end play before called DAO
        if (game.getBoard() == "----") {
            System.out.println("That game doesn't exist. Please select a valid game.");
            return null;
        } else if (game.getIsComplete()) {
            System.out.println("That game is already complete. Please try a different game or start a new one.");
            return null;
        }
        
        String guess = round.getGuess();
        if (!guess.matches("^(?!.*(.).*\\1)\\d{4}$")) {
            System.out.println("\"" + guess  + "\" is not a valid entry. You must enter four unique numbers from 0-9.");
            return null;
        }
        
        String board = game.getBoard();
        char[] guessArray = guess.toCharArray();
        char[] boardArray = board.toCharArray();
        
        
        // iterate through array of characters to find
        // exact and partial matches
        for (int i = 0; i < 4; i++) {
            if (boardArray[i] == guessArray[i]) {
                exactMatches++;
            } else if (board.contains(String.valueOf(guessArray[i]))) {
                partialMatches++;
            }
        }
        
        round.setMatches("e:" + exactMatches + ":p" + partialMatches);
        dao.guess(round);
        
        if (exactMatches == 4) {
            dao.endGame(gameId);
            int numberOfRoundsToWin = dao.gameRounds(gameId).size();
            System.out.println("Congratulations! You have completed game " + String.valueOf(gameId) + " in " + numberOfRoundsToWin + " rounds.");

        }
        
        return round;
        
    }

    @Override
    public List<Game> getGamesList() {
        List<Game> gamesList = dao.getAllGames();
        List<Game> filteredGamesList = new ArrayList();
       gamesList.stream().forEach(game -> {
           if (game.getIsComplete() == false) {
               game.setBoard("GAME BOARD HIDDEN UNTIL GAME IS WON");
           }
           filteredGamesList.add(game);
       });

        return filteredGamesList;
        
    }

    @Override
    public List<Round> getRounds(int gameId) {
        List<Round> allRounds = dao.gameRounds(gameId);
        
        return allRounds;
    }

    @Override
    public Game getSpecificGame(int gameId) {
        Game selectedGame = dao.game(gameId);
        if (selectedGame == null) {
            Game deadGame = new Game();
            deadGame = new Game();
            deadGame.setGameId(gameId);
            deadGame.setBoard("NOT A VALID GAME NUMBER");
            deadGame.setIsComplete(true);
            return deadGame;
        }
        if (selectedGame.getIsComplete()) {
            selectedGame.setBoard("GAME BOARD HIDDEN UNTIL GAME IS WON");
        }
        return selectedGame;
    }

}
