package com.hangmanws.game.boundry;

import com.hangmanws.game.entity.Game;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Piotr S.
 */
@Path("game")
public class GameResource {

    @Inject
    GameService gs;

    /**
     * Provides list of all games
     *
     * @return List of Games in Json format
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> allGames() {
        List<Game> list = gs.getAllGames();
        //list.add(new Game("Poland",new Str)));ingBuilder("-----")));
        return list;
    }

    /**
     * Provides data of the client's guess
     * http://localhost:8080/HangmanWS/resources/game przyk³adowe dane:
     * {"game_id":"1","letter":"a"}
     *
     * @param incomingData
     * @return updated Game
     * @throws Exception
     */
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response guess(String incomingData) throws Exception {

        Game game;
        try {

            JsonReader reader = Json.createReader(new StringReader(incomingData));
            JsonObject object = reader.readObject();
            reader.close();
            System.out.println("object " + object);
//			long game_id = object.getInt("game_id");
            String strLetter = object.getString("letter").toLowerCase();
            System.out.println("strLetter " + strLetter);
            char letter = strLetter.charAt(0);
            long game_id = Integer.parseInt(object.getString("game_id"));
            System.out.println(game_id + " - " + letter);
            game = this.gs.makeGuess(game_id, letter);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Server was not able to process your request").build();
        }

        return Response.ok(game).build();
    }
    
    /**
     * Method creates new Game with random word and gives it id
     * @return object of Game in Json format
     */
    @Path("/newGame")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Game newGame() {
        return gs.addNewGame();
    }
}
