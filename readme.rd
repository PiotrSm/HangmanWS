Java coding test

The challenge is to build a simple version of a 'hangman' game as a web-app (look & feel is not important). Our focus is on the server-side, but the app needs a client side to play the game (any browser tech as long as it's easy to install&run). The app should keep the current game state persistent across server and browser re-starts. The app could be used by many users at the same time. The app build process can be any technology, though we'd prefer 'gradle', and could produce a war file that can be deployed in a tomcat or a jar for a standalone JVM server. 

Server side Rest methods:
1. GET /HangmanWS/resources/game - gives all the games in Json format
2. GET /HangmanWS/resources/game/newGame - creates new Game with random word, gives it id and returns the new Game in Json format
3. POST /HangmanWS/resources/game with data in Json format e.g. {"game_id":"1","letter":"h"} make guess of the letter in the game