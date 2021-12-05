/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.service;

import com.sv.mastermind.model.Game;
import com.sv.mastermind.model.Round;
import java.util.List;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public interface MastermindServiceLayer {
    
    Game createGame();
    Round playRound(Round round);
    List<Game> getGamesList();
    List<Round> getRounds(int gameId);
    Game getSpecificGame(int gameId);
    
}
