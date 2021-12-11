/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sv.mastermind.data;

import com.sv.mastermind.model.Game;
import com.sv.mastermind.model.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;  


/**
 *
 * @author: Steven Vallarsa email: stevenvallarsa@gmail.com date: purpose:
 */
@Repository
public class MastermindDatabaseDao implements MastermindDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MastermindDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game newGame(Game game) {

        final String sql = "INSERT INTO game(board) VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getBoard());
            return statement;
        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public Round guess(Round round) {
        final String sql = "INSERT INTO round(guess, matches, gameId, timeOfPlay) VALUES(?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getGuess());
            statement.setString(2, round.getMatches());
            statement.setInt(3, round.getGameId());
            statement.setTimestamp(4, Timestamp.valueOf(round.getTimeOfPlay()));
            return statement;
        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Game> getAllGames() {

        final String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, new GameMapper());

    }

    @Override
    public Game game(int gameId) {
        final String sql = "SELECT * FROM game WHERE gameId = ?";
        Game returnedGame = new Game();
        
        try {
             returnedGame = jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
        } catch (DataAccessException e) {
            returnedGame.setBoard("That game doesn't exist.");
        }
        
        return returnedGame;
    }

    @Override
    public void endGame(int gameId) {

        final String sql = "UPDATE game SET isComplete = true WHERE gameId = ?";

        jdbcTemplate.update(sql, gameId);
    }

    @Override
    public List<Round> gameRounds(int gameId) {

        final String sql = "SELECT * FROM round WHERE gameId = ? ORDER BY roundId DESC";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);

    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game g = new Game();
            g.setGameId(rs.getInt("gameId"));
            g.setBoard(rs.getString("board"));
            g.setIsComplete(rs.getBoolean("isComplete"));
            g.setInfo(rs.getString("info"));
            return g;
        }
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round r = new Round();
            r.setRoundId(rs.getInt("roundId"));
            r.setGuess(rs.getString("guess"));
            r.setMatches(rs.getString("matches"));
            r.setGameId(rs.getInt("gameId"));
            r.setTimeOfPlay(rs.getTimestamp("timeOfPlay").toLocalDateTime());
            return r;
        }
    }

}
