package com.hangmanws.game.boundry;

import com.hangmanws.game.controler.GameControler;
import com.hangmanws.game.entity.Game;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Piotr S.
 */
@Stateless
public class GameService {

    Random random = new Random();
    String[] guesses = {"plum", "banana", "apple", "orange", "strawberry", "butter","bread","shop"};

    @Inject
    GameControler gc;

    public List<Game> getAllGames() {
        return gc.all();
    }

    public Game save(Game g) {
        return gc.save(g);
    }

    public Game addNewGame() {
        String newWord = guesses[random.nextInt(guesses.length)];
        StringBuilder strBud = new StringBuilder(newWord.replaceAll(".", "_"));

        return gc.save(new Game(newWord, strBud));
    }

    public Game makeGuess(long game_id, char letter) {
        Game gm = gc.getGameById(game_id);
        return gm.updateGuessLetter(letter);
    }

}
