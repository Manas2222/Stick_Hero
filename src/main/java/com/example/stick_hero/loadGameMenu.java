package com.example.stick_hero;

import java.util.ArrayList;

public class loadGameMenu {
    ArrayList<Game> loadList = new ArrayList<>();

    public ArrayList<Game> getLoadList() {
        return loadList;
    }

    public void setLoadList(ArrayList<Game> loadList) {
        this.loadList = loadList;
    }

    public ArrayList<Game> displaySavedGames(){
        return loadList;
    }

    public Game selectLoadedGame(){
        return new Game();
    }
}
