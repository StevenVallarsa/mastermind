/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.model;

import java.time.LocalDateTime;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class Round {

    private int roundId;
    private String guess;
    private String matches;
    private int gameId;

    public int getRoundId() {
        return roundId;
    }
    
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    
}
