package com.hangmanws.game.controler;

import com.hangmanws.game.entity.Game;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piotr S.
 */
public class GameControler {

    @PersistenceContext
    EntityManager em;

    public Game save(Game g) {
        em.persist(g);
        em.flush();
        return g;
    }

    public List<Game> all() {
        return this.em.createNamedQuery("all", Game.class).getResultList();
    }

    public Game getGameById(long game_id) {
        return em.find(Game.class, game_id);
    }

}
