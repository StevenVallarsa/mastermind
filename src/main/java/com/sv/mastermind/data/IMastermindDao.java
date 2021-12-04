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
public interface IMastermindDao {
    Game newGame(Game game);
    Round guess(String currentGuess);
    List<Game> games();
    Game game(int gameId);
    List<Round> gameRounds(int gameId);
}
