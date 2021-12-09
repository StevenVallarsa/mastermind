/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.mastermind.controllers;

import com.sv.mastermind.data.MastermindDao;
import com.sv.mastermind.model.Game;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author: Steven Vallarsa
 * email: stevenvallarsa@gmail.com
 * date:
 * purpose:
 */
@Controller
public class HTMLcontroller {
    
    @Autowired
    MastermindDao dao;
    
    @GetMapping("games")
    public String displayGames(Model model) {
        List<Game> games = dao.getAllGames();
        model.addAttribute("games", games);
        return "games";
    }

}
