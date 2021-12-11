/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.sv.mastermind;

/**
 *
 * @author StevePro
 */
public enum Ranking {
    MASTERBRAIN("Masterbrain"),
    MASTER("Master"),
    PRETTY_GOOD("Pretty Good"),
    GOOD("Good"),
    UGH("Ugh"),
    NO_RANKING("No Ranking For You!");

    private String value;
            
    Ranking(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
        
}
