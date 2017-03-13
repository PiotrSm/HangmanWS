package com.hangmanws.game.entity;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Piotr S.
 */
@XmlRootElement
@Entity
@NamedQuery(name = "all", query = "SELECT g FROM Game g")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long game_id;
    private String mysteryWord;
    private StringBuilder currentGuess;
    private ArrayList<Character> previousGuesses = new ArrayList<>();

    private int maxTries = 6;
    private int currentTry = 0;
    private boolean gameOver = false, won = false;

    public Game(String mysteryWord, StringBuilder currentGuess) {
        this.mysteryWord = mysteryWord;
        this.currentGuess = currentGuess;
    }

    public Game() {
    }
    
    /**
     * Method updating Game object according to provided guessing letter
     * @param char letter
     * @return Geme object
     */
    public Game updateGuessLetter(char letter) {
        boolean isItAGoodGuess = false;
        if (this.mysteryWord.indexOf(letter) == -1) {
            currentTry++;
            this.previousGuesses.add(letter);
        } else {
            for (int i = 0; i < mysteryWord.length(); i++) {
                if (mysteryWord.charAt(i) == letter) {
                    currentGuess.setCharAt(i, letter);
                    isItAGoodGuess = true;
                }
            }
        }
        won = mysteryWord.equals(currentGuess);
        if (won || this.currentTry == this.maxTries) {
            this.gameOver = true;
        }
        return this;
    }

}
