package com.example.stick_hero;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public interface SceneManagerInterface {
    public void switchToGame(MouseEvent event) throws IOException;
    public void exitGame() throws IOException;
    public void switchToInstructions(MouseEvent event) throws IOException;
    public void switchToMainMenu(ActionEvent event) throws IOException;
    public void switchToHighScorePage(MouseEvent event) throws IOException;
}
