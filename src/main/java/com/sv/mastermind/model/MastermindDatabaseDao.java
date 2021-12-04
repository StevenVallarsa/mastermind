/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.model;

import com.sv.mastermind.data.IMastermindDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
public class MastermindDatabaseDao implements IMastermindDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    public MastermindDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    @Override
    public Game newGame(Game game) {

        final String sql = "INSERT INTO mastermind(gameId, board) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, game.getGameId());
            statement.setString(2, game.getBoard());
            return statement;
        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        
        return game;
    }        

    @Override
    public Round guess(String currentGuess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> games() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game game(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> gameRounds(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
