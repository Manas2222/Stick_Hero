package com.example.stick_hero;

import java.util.ArrayList;

public class mainMenu {
    Game activeGame;
    ArrayList<Game> savedGames = new ArrayList<Game>();

    public Game getActiveGame() {
        return activeGame;
    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }

    public ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(ArrayList<Game> savedGames) {
        this.savedGames = savedGames;
    }

//    public Game newGame(){
//        return new Game();
//    }

    public void loadGame(){

    }

//    public Game resumeGame(){
//        return new Game();
//    }

    public void exit(){}

}
