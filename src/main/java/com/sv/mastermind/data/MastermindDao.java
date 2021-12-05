/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sv.mastermind.data;

import com.sv.mastermind.model.Game;
import com.sv.mastermind.model.Round;
import java.util.List;

/**
 *
 * @author StevePro
 */
public interface MastermindDao {
    Game newGame(Game game);
    Round guess(Round round);
    List<Game> getAllGames();
    Game game(int gameId);
    void endGame(int gameId);
    List<Round> gameRounds(int gameId);
}
