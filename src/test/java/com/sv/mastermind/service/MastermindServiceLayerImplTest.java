/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sv.mastermind.service;

import com.sv.mastermind.TestApplicationConfiguration;
import com.sv.mastermind.model.Round;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author StevePro
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MastermindServiceLayerImplTest {
    
    @Autowired
    MastermindServiceLayer service;
    
    public MastermindServiceLayerImplTest() {
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
    public void testNoMatchesInRound() {
       Round testRound = new Round();
       testRound.setGuess("5678");
       testRound.setGameId(100);
       testRound.setTimeOfPlay(LocalDateTime.of(2020, 02, 02, 02, 02, 02));
       
       Round returnedRound = service.playRound(testRound);
       
//       testRound.setRoundId(1000);
       Assert.assertEquals(testRound, returnedRound);
       
       
       Round newTestRound = new Round();
       newTestRound.setGuess("0000");
       newTestRound.setGameId(999);
       newTestRound.setTimeOfPlay(LocalDateTime.of(2222, 02, 02, 02, 02, 02));

       Assert.assertNotSame(returnedRound, newTestRound);
        
        
    }
    
}
