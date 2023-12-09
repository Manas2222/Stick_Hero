package com.example.stick_hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    public static Scene game_scene;
    public static String abs_path = "E:\\Programming\\Assignments_Sem3\\Stick_Hero\\src\\main\\resources\\com\\example\\stick_hero\\";

    @Override
    public void start(Stage stage) throws IOException {
        try {

            //setting up the background sound that was needed in the game.

            Media media = new Media(new File(HelloApplication.abs_path+ "Interstellar-Theme.mp3").toURI().toString());
            MediaPlayer bgPlayer = new MediaPlayer(media);
            if(bgPlayer != null){
                bgPlayer.play();
            }



            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GamePlay.fxml"));
            game_scene = new Scene(fxmlLoader.load());

            //Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Stick Hero");
            stage.setScene(game_scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }



}