package com.example.stick_hero;

public class activeGame extends Game{
    int currentHighscore;
    int cherryCount;
    int currentScore;

    public int getCurrentHighscore() {
        return currentHighscore;
    }

    public void setCurrentHighscore(int currentHighscore) {
        this.currentHighscore = currentHighscore;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
