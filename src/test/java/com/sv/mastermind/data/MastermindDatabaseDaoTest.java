/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sv.mastermind.data;

import com.sv.mastermind.TestApplicationConfiguration;
import com.sv.mastermind.model.Game;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author StevePro
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MastermindDatabaseDaoTest {
    
    @Autowired
    MastermindDao dao;
    
    public MastermindDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGame() {
        Game game = new Game();
        game.setGameId(100);
        game.setBoard("1234");
        game.setIsComplete(true);
        
        Game fromDao = dao.newGame(game);
        
        assertEquals(game, fromDao);
        
    }
    
    @Test
    public void testGameNumber() {
        Game fromDao = dao.game(100);
        assertTrue("1234".equals(fromDao.getBoard()));
    }
    
}
